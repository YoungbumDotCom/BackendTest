package emiyaj.user

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import emiyaj.util.AESEncryption

/**
 * This data class represents a login request in the application.
 * It includes a data property which is the encrypted login data received in the request.
 * It also includes a decryptedData property which is the decrypted login data.
 *
 * @property data The encrypted login data received in the request.
 * @property decryptedData The decrypted login data. It is decrypted using the AESEncryption utility.
 */
data class LoginRequest @JsonCreator constructor(@JsonProperty val data: String) {
    val decryptedData = AESEncryption.decrypt(data)
}