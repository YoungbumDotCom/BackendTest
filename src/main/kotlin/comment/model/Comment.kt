package emiyaj.comment.model

import java.util.*

/**
 * This data class represents a Comment in the application.
 * It includes various properties related to the comment, such as nickname, IP address, comment text, comment time, and villager code.
 *
 * @property nickname The nickname of the user who posted the comment.
 * @property ip The IP address of the user who posted the comment.
 * @property comment The text of the comment.
 * @property commented_at The time when the comment was posted, represented as a Unix timestamp.
 * @property villagerCode The code of the villager for whom the comment was posted.
 */
data class Comment (
    val nickname: String,
    val ip: String,
    val comment: String,
    val commented_at: Long,
    val villagerCode: String
)