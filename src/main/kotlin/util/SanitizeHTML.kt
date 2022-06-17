package emiyaj.util

object SanitizeHTML {
    private val forbiddenTags = listOf(
            "script",
            "iframe",
            "applet",
            "style",
            "link",
    )
    fun String.sanitizeHTML(): String {
        forbiddenTags.forEach { tag ->
            this.replace("<$tag>", "")
            this.replace("</$tag>", "")
        }
        return this
    }
}