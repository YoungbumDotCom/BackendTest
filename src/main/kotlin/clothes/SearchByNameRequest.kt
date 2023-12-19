package emiyaj.clothes

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import emiyaj.util.AESEncryption

/**
 * This data class represents the request for searching a clothing item by name.
 * The request data is encrypted and will be decrypted upon creation of the object.
 *
 * @property data The encrypted data of the request.
 * @property decryptedData The decrypted data of the request.
 */
data class SearchByNameRequest @JsonCreator constructor(@JsonProperty val data: String) {
    val decryptedData = AESEncryption.decrypt(data)
}