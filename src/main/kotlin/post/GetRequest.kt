package emiyaj.post

import kotlinx.serialization.Serializable

@Serializable
data class GetRequest(
    val start: Int,
    val end: Int,
)