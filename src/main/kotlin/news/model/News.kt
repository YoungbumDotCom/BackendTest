package emiyaj.news.model

import NewsContentLocale
import java.util.*

data class News (
        val title: NewsContentLocale,
        val content: NewsContentLocale,
        val author: String,
        val important: Boolean,
        val created_at: Long = Calendar.getInstance().timeInMillis
)