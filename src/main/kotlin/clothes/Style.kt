package emiyaj.clothes

/**
 * This enum class represents the style of a clothing item.
 * Each enum value corresponds to a specific style.
 *
 * @property value The value related to the style of the clothing item.
 *
 * The companion object provides a method to get the style by its value.
 */
enum class Style(val value: String) {
    /**
     * This style represents a clothing item that is active.
     */
    Active("active"),

    /**
     * This style represents a clothing item that is simple.
     */
    Simple("simple"),

    /**
     * This style represents a clothing item that is cool.
     */
    Cool("cool"),

    /**
     * This style represents a clothing item that is cute.
     */
    Cute("cute"),

    /**
     * This style represents a clothing item that is elegant.
     */
    Elegant("elegant"),

    /**
     * This style represents a clothing item that is gorgeous.
     */
    Gorgeous("gorgeous"),

    /**
     * This style represents a clothing item that has not implemented a specific style.
     */
    NotImplemented("null");

    companion object {
        /**
         * This method gets the style by its value.
         *
         * @param value The value of the style.
         * @return The style corresponding to the given value, or null if no match is found.
         */
        fun getByValue(value: String?): Style? {
            return values().find { it.value == value }
        }
    }
}