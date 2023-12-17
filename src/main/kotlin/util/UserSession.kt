package emiyaj.util

import emiyaj.user.model.User

/**
 * This object represents a user session in the EmiyaJ application.
 * It provides methods to authenticate, create, destroy, and manage user sessions.
 */
object UserSession {
    // A mutable map to store the user sessions. The key is the session token and the value is the User object.
    private val session: MutableMap<String, User> = mutableMapOf()

    /**
     * This method is used to authenticate a user based on the provided session token.
     * @param token The session token of the user.
     * @return The User object if the session token is valid, null otherwise.
     */
    fun authenticate(token: String): User? = session[token]

    /**
     * This method is used to create a new user session.
     * It generates a random session token, stores the User object in the session map, and returns the session token.
     * @param user The User object.
     * @return The session token.
     */
    fun createSession(user: User): String {
        val token = generateRandomString()
        session[token] = user
        return token
    }

    /**
     * This method is used to destroy a user session.
     * It finds the session token associated with the User object and removes it from the session map.
     * @param user The User object.
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
     * This method is used to destroy all user sessions.
     * It clears the session map.
     */
    fun destroyAll() {
        session.clear()
    }

    /**
     * This method is used to generate a random string of a specified length.
     * It's used internally to generate session tokens.
     * @param length The length of the random string. The default value is 35.
     * @return The generated random string.
     */
    private fun generateRandomString(length: Int = 35): String {
        val chars = ('A'..'Z') + ('a'..'z') + (0..9)
        return (1..length).map { chars.random() }.joinToString("")
    }
}