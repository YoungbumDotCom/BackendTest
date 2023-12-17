package emiyaj.news.model

/**
 * This data class represents the locale-specific content of a News item in the application.
 * It includes properties for English, Korean, and Japanese versions of the content.
 *
 * @property en The English version of the content.
 * @property ko The Korean version of the content.
 * @property ja The Japanese version of the content.
 */
data class NewsContentLocale (
        val en: String,
        val ko: String,
        val ja: String
)