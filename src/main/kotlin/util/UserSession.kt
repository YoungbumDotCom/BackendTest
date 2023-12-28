package emiyaj.util

import emiyaj.user.model.User

/**
 * 이 객체는 EmiyaJ 애플리케이션에서 사용자 세션을 나타냅니다.
 * 사용자를 인증하고, 세션을 생성하고, 파괴하고, 관리하는 메서드를 제공합니다.
 */
object UserSession {
    // 사용자 세션을 저장하기 위한 가변 맵입니다. 키는 세션 토큰이고 값은 User 객체입니다.
    private val session: MutableMap<String, User> = mutableMapOf()

    /**
     * 이 메서드는 제공된 세션 토큰을 기반으로 사용자를 인증하는 데 사용됩니다.
     * @param token 사용자의 세션 토큰입니다.
     * @return 세션 토큰이 유효하면 User 객체를 반환하고, 그렇지 않으면 null을 반환합니다.
     */
    fun authenticate(token: String): User? = session[token]

    /**
     * 이 메서드는 새로운 사용자 세션을 생성하는 데 사용됩니다.
     * 무작위 세션 토큰을 생성하고, User 객체를 세션 맵에 저장하고, 세션 토큰을 반환합니다.
     * @param user User 객체입니다.
     * @return 세션 토큰입니다.
     */
    fun createSession(user: User): String {
        val token = generateRandomString()
        session[token] = user
        return token
    }

    /**
     * 이 메서드는 사용자 세션을 파괴하는 데 사용됩니다.
     * User 객체와 연관된 세션 토큰을 찾아 세션 맵에서 제거합니다.
     * @param user User 객체입니다.
     */
    fun destroySession(user: User) {
        val tokens = session.filterValues { it == user }.keys
        if (tokens.isEmpty() || tokens.size != 1) {
            return
        } else {
            val token = tokens.first()
            session.remove(token)
        }
    }

    /**
     * 이 메서드는 모든 사용자 세션을 파괴하는 데 사용됩니다.
     * 세션 맵을 지웁니다.
     */
    fun destroyAll() {
        session.clear()
    }

    /**
     * 이 메서드는 지정된 길이의 무작위 문자열을 생성하는 데 사용됩니다.
     * 내부적으로 세션 토큰을 생성하는 데 사용됩니다.
     * @param length 무작위 문자열의 길이입니다. 기본값은 35입니다.
     * @return 생성된 무작위 문자열입니다.
     */
    private fun generateRandomString(length: Int = 35): String {
        val chars = ('A'..'Z') + ('a'..'z') + (0..9)
        return (1..length).map { chars.random() }.joinToString("")
    }
}