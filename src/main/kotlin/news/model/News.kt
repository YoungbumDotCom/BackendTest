package emiyaj.news.model

import java.util.*

/**
 * This data class represents a News item in the application.
 * It includes various properties related to the news item, such as title, content, author, importance, and creation time.
 *
 * @property title The title of the news item, represented by a emiyaj.news.model.NewsContentLocale object.
 * @property content The content of the news item, represented by a emiyaj.news.model.NewsContentLocale object.
 * @property author The author of the news item.
 * @property important A boolean indicating whether the news item is marked as important.
 * @property created_at The creation time of the news item, represented as a Unix timestamp. Default is the current time.
 */
data class News (
        val title: NewsContentLocale,
        val content: NewsContentLocale,
        val author: String,
        val important: Boolean,
        val created_at: Long = Calendar.getInstance().timeInMillis
)