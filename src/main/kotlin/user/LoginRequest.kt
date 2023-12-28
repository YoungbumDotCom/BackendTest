package emiyaj.user

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * This data class represents a login request in the application.
 * It includes a data property which is the encrypted login data received in the request.
 * It also includes a decryptedData property which is the decrypted login data.
 *
 * @property data The login data received in the request.
 */
data class LoginRequest @JsonCreator constructor(@JsonProperty val data: String)