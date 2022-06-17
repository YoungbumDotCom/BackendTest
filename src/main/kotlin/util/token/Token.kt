package emiyaj.util.token

data class Token (
        val header: TokenHeader,
        val issuedFor: String,
        val token: String,
        val expireTime: Long,
        val createdTime: Long,
        val cleanTime: Long
)