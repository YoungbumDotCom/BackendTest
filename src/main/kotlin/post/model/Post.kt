package emiyaj.comment.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable


/**
 * 이 데이터 클래스는 애플리케이션에서 게시물을 나타냅니다.
 * 게시물의 ID, 작성자, 내용, 이미지 경로, 등록 날짜를 포함합니다.
 *
 * @property id 게시물의 ID입니다. 이것은 게시물에 대한 고유 식별자입니다.
 * @property author 게시물의 작성자입니다. 이것은 게시물을 작성한 사용자의 ID를 나타냅니다.
 * @property content 게시물의 내용입니다. 이것은 게시물의 본문을 나타냅니다.
 * @property imageKey 게시물의 이미지 키입니다. 이것은 게시물에 첨부된 이미지의 키를 나타냅니다.
 * @property created 게시물의 등록 날짜입니다. 이것은 게시물이 등록된 날짜를 나타냅니다
 */
@Serializable
data class Post(
    val id: Int,
    val author: Int,
    val content: String,
    val imageKey: String,
    val created: LocalDateTime
)