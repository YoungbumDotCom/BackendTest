package emiyaj.comment.model

import java.util.*

data class Comment (
    val nickname: String,
    val ip: String,
    val comment: String,
    val commented_at: Long,
    val villagerCode: String
)