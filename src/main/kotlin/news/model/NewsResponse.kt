package emiyaj.news.model

/**
 * This data class represents a response to a news request in the application.
 * It includes a list of News items to be included in the response.
 *
 * @property news The list of News items to be included in the response.
 */
data class NewsResponse (
        val news: List<News>
)