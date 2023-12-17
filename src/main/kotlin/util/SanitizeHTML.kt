package emiyaj.util

/**
 * This object represents a utility for sanitizing HTML strings in the EmiyaJ application.
 * It provides a method to sanitize HTML strings by removing forbidden tags.
 *
 * Example usage:
 * ```kotlin
 * val sanitizedHTML = "<script>alert('Hello, world!')</script>".sanitizeHTML()
 */
object SanitizeHTML {
     // A list of forbidden HTML tags that should be removed from the HTML strings.
     private val forbiddenTags = listOf( "script", "iframe", "applet", "style", "link", )

    /**
     * This method is used to sanitize an HTML string by removing the forbidden tags.
     * It iterates over the forbidden tags and removes them from the HTML string.
     *
     * Example usage:
     * ```kotlin
     * val sanitizedHTML = "<script>alert('Hello, world!')</script>".sanitizeHTML()
     * ```
     *
     * @receiver The HTML string to be sanitized.
     * @return The sanitized HTML string.
     */
    fun String.sanitizeHTML(): String {
        forbiddenTags.forEach { tag ->
            this.replace("<$tag>", "")
            this.replace("</$tag>", "")
        }
        return this
    }
}