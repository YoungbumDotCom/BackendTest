package emiyaj.common

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import emiyaj.util.AESEncryption

/**
 * This class represents a common request in the application.
 * It includes a data property which is the encrypted request data received in the request.
 * It also includes a decryptedData property which is the decrypted request data.
 *
 * @property data The encrypted request data received in the request.
 * @property decryptedData The decrypted request data. It is decrypted using the AESEncryption utility.
 *
 * @constructor Creates a new CommonRequest object.
 * It is annotated with @JsonCreator to specify that it should be used for JSON deserialization.
 * The data parameter is annotated with @JsonProperty to specify that it should be used for JSON property binding.
 *
 * @param data The encrypted request data.
 */
class CommonRequest @JsonCreator constructor(@JsonProperty val data: String) {
    val decryptedData = AESEncryption.decrypt(data)
}