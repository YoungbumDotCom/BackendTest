package emiyaj.util.token

/**
 * This data class represents the header of a token in the application.
 * It includes the algorithm used for the token. The default algorithm is "AES256".
 *
 * @property algorithm The algorithm used for the token. Default is "AES256".
 */
data class TokenHeader (
        val algorithm: String = "AES256"
)