package emiyaj.util.token

/**
 * This data class represents a token in the application.
 * It includes the token header, the entity for which the token was issued, the token string itself,
 * and timestamps for the token's expiration, creation, and cleanup times.
 *
 * @property header The header of the token, represented by a TokenHeader object.
 * @property issuedFor The entity for which the token was issued. This could be a user ID, username, etc.
 * @property token The token string itself. This is typically a Base64 encoded string.
 * @property expireTime The expiration time of the token, represented as a Unix timestamp.
 * @property createdTime The creation time of the token, represented as a Unix timestamp.
 * @property cleanTime The cleanup time of the token, represented as a Unix timestamp. This is typically the time at which the token should be removed from memory or a database.
 */
data class Token (
        val header: TokenHeader,
        val issuedFor: String,
        val token: String,
        val expireTime: Long,
        val createdTime: Long,
        val cleanTime: Long
)