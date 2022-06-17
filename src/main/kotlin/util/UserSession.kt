package emiyaj.util

import emiyaj.user.model.User

object UserSession {
    private val session: MutableMap<String, User> = mutableMapOf()

    fun authenticate(token: String): User? = session[token]

    fun createSession(user: User): String {
        val token = generateRandomString()
        session[token] = user
        return token
    }

    fun destroySession(user: User) {
        val tokens = session.filterValues { it == user }.keys
        if (tokens.isEmpty() || tokens.size != 1) {
            return
        } else {
            val token = tokens.first()
            session.remove(token)
        }
    }

    fun destroyAll() {
        session.clear()
    }

    private fun generateRandomString(length: Int = 35): String {
        val chars = ('A'..'Z') + ('a'..'z') + (0..9)
        return (1..length).map { chars.random() }.joinToString("")
    }
}