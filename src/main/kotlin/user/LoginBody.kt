package emiyaj.user

/**
 * This data class represents the body of a login request in the application.
 * It includes the username and password for authentication, and optionally an IP address.
 *
 * @property username The username for authentication.
 * @property password The password for authentication.
 * @property ipAddress The IP address of the client making the request. This is optional and defaults to null.
 */
data class LoginBody (
        val username: String,
        val password: String,
        val ipAddress: String? = null
)