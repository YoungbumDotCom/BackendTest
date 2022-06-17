package emiyaj.comment

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import emiyaj.util.AESEncryption

data class PostRequest @JsonCreator constructor(
    @JsonProperty val nickname: String,
    @JsonProperty val data: String,
    @JsonProperty val comment: String
) {
    val code = AESEncryption.decrypt(data)
}