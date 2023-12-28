package emiyaj.comment

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty


data class PostRequest @JsonCreator constructor(
    @JsonProperty val image: Int,
    @JsonProperty val content: String,
)