package emiyaj.util

import com.beust.klaxon.Klaxon
import emiyaj.user.model.User
import emiyaj.user.UserStatus
import emiyaj.util.token.Token
import emiyaj.util.token.TokenHeader
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

    /**
     * Create session using the User object
     *
     * @param user User object
     * @return AES encrypted whole user token.
     */
    fun createSession(user: User): String {
        val expireTime = Calendar.getInstance()
        val cleanTime = Calendar.getInstance()
        val now = Calendar.getInstance().timeInMillis
        expireTime.add(Calendar.MINUTE, expireMinutes)
        cleanTime.add(Calendar.MINUTE, cleanMinutes)
        val tokenValue = generateRandomString(32)
        val tokenBody = Token(TokenHeader(), "dodo.ij.rs", tokenValue, expireTime.timeInMillis, now, cleanTime.timeInMillis)
        val encryptedTokenBodyJson = AESEncryption.encrypt(Klaxon().toJsonString(tokenBody), KeyManager.tokenKey, KeyManager.tokenIv)
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
        val decrypted = AESEncryption.decrypt(encryptedToken, KeyManager.tokenKey, KeyManager.tokenIv)
        val tokenBody = Klaxon().parse<Token>(decrypted) ?: return Pair(UserStatus.INVALID, null)
        val now = Calendar.getInstance().timeInMillis
        if (tokenBody.expireTime <= now) return Pair(UserStatus.EXPIRED, null)
        // if (tokenBody.issuedFor != host) return Pair(UserStatus.NOT_ALLOWED, null)
        val user = sessions[tokenBody] ?: return Pair(UserStatus.INVALID, null)
        cleanExpiredToken()
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