package emiyaj.clothes

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import emiyaj.util.AESEncryption

data class SearchByNameRequest @JsonCreator constructor(@JsonProperty val data: String) {
    val decryptedData = AESEncryption.decrypt(data)
}