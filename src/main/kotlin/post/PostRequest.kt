package emiyaj.comment

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty


data class PostRequest @JsonCreator constructor(
    @JsonProperty val image: String,
    @JsonProperty val content: String,
)