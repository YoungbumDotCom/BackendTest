package emiyaj.user

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import emiyaj.util.AESEncryption

data class LoginRequest @JsonCreator constructor(@JsonProperty val data: String) {
    val decryptedData = AESEncryption.decrypt(data)
}