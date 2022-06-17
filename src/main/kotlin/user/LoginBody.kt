package emiyaj.user

data class LoginBody (
        val username: String,
        val password: String,
        val ipAddress: String? = null
)