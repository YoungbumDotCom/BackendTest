package emiyaj.util

import emiyaj.user.model.User
import emiyaj.user.UserStatus
import emiyaj.util.token.Token
import emiyaj.util.token.TokenHeader
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

/**
 * EmiyaJ user token management.
 *
 * @property expireMinutes Expire time
 * @property cleanMinutes After marked as expired, still on storage, after this time, remove from storage.
 * @property sessions Session storage. (Decrypted token object, user object)
 */
object UserSessionNew {
    private const val expireMinutes = 3
    private const val cleanMinutes = 40
    private val sessions: MutableMap<Token, User> = mutableMapOf()
    private val encryption = AESEncryption()

    /**
     * Create session using the User object
     *
     * @param user User object
     * @return AES encrypted whole user token.
     */
    fun createSession(user: User): String {
        if (sessions.containsValue(user)) {
            val token = sessions.filter { it.value == user }.keys.first()
            return encryption.encrypt(Json.encodeToString(token))
        }
        val expireTime = Calendar.getInstance()
        val cleanTime = Calendar.getInstance()
        val now = Calendar.getInstance().timeInMillis
        expireTime.add(Calendar.MINUTE, expireMinutes)
        cleanTime.add(Calendar.MINUTE, cleanMinutes)
        val tokenValue = generateRandomString(32)
        val tokenBody = Token(TokenHeader(), "test", tokenValue, expireTime.timeInMillis, now, cleanTime.timeInMillis)
        val encryptedTokenBodyJson =
            encryption.encrypt(Json.encodeToString(tokenBody))//Klaxon().toJsonString(tokenBody))
        sessions[tokenBody] = user
        return encryptedTokenBodyJson
    }

    /**
     * Authenticate using AES encrypted token string, verify token is coming from the same place.
     *
     * @param encryptedToken AES encrypted token string
     * @param host Request website's host
     * @return Pair of user status, User object. Expired, Invalid, Authorized. If not authorized, return null
     * @see UserStatus For detail about UserStatus.
     */
    fun authenticate(encryptedToken: String, host: String?): Pair<UserStatus, User?> {
        // AES로 암호화된 토큰을 복호화합니다.
        val decrypted = encryption.decrypt(encryptedToken)

        // 복호화된 토큰을 Token 객체로 변환합니다.
        // 만약 복호화된 토큰이 유효하지 않은 경우, INVALID 상태와 함께 null을 반환합니다.
        val tokenBody = try {
            Json.decodeFromString<Token>(decrypted)
        } catch (e: Exception) {
            return Pair(UserStatus.INVALID, null)
        }

        // 현재 시간을 밀리초 단위로 가져옵니다.
        val now = Calendar.getInstance().timeInMillis

        // 토큰의 만료 시간이 현재 시간보다 이전인 경우, EXPIRED 상태와 함께 null을 반환합니다.
        if (tokenBody.expireTime <= now) return Pair(UserStatus.EXPIRED, null)

        // 세션에서 토큰 본문에 해당하는 사용자를 가져옵니다.
        // 해당 사용자가 없는 경우, INVALID 상태와 함께 null을 반환합니다.
        val user = sessions[tokenBody] ?: return Pair(UserStatus.INVALID, null)

        // 만료된 토큰을 정리합니다.
        cleanExpiredToken()

        // AUTHORIZED 상태와 사용자를 반환합니다.
        return Pair(UserStatus.AUTHORIZED, user)
    }

    /**
     * Find clean time ran out tokens, remove from sessions permanently.
     */
    private fun cleanExpiredToken() {
        val now = Calendar.getInstance().timeInMillis
        val expiredTokens = sessions.filter { it.key.cleanTime <= now }
        for (token in expiredTokens) {
            sessions.remove(token.key)
        }
    }

    /**
     * 주어진 암호화된 토큰을 사용하여 세션을 무효화하는 함수입니다.
     * 이 함수는 AES 암호화를 사용하여 토큰을 복호화하고, 복호화된 토큰을 Token 객체로 변환합니다.
     * 그런 다음, 세션에서 해당 토큰을 제거하여 세션을 무효화합니다.
     *
     * @param encryptedToken 세션을 무효화할 암호화된 토큰 문자열입니다.
     */
    fun invalidate(encryptedToken: String) {
        val decrypted = encryption.decrypt(encryptedToken)
        val tokenBody = try {
            Json.decodeFromString<Token>(decrypted)
        } catch (e: Exception) {
            return
        }
        sessions.remove(tokenBody)
    }

    /**
     * Generate random string for a new token string.
     *
     * @param length Length of random string. Default: 64
     * @return Randomly generated string.
     */
    private fun generateRandomString(length: Int = 64): String {
        val chars = ('A'..'Z') + ('a'..'z') + (0..9)
        return (1..length).map { chars.random() }.joinToString("")
    }
}