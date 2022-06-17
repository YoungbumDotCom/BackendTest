package emiyaj.clothes

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import emiyaj.util.AESEncryption

data class SearchByVillagerRequest @JsonCreator constructor(@JsonProperty val data: String, @JsonProperty val target: Int, @JsonProperty val index: Int?) {
  val decryptedData = AESEncryption.decrypt(data)
}