package emiyaj.common

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import emiyaj.util.AESEncryption

class CommonRequest @JsonCreator constructor(@JsonProperty val data: String) {
    val decryptedData = AESEncryption.decrypt(data)
}