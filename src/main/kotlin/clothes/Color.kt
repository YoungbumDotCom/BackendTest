package emiyaj.clothes

/**
 * This enum class represents the color of a clothing item.
 * Each enum value corresponds to a specific color.
 *
 * @property value The value related to the color of the clothing item.
 *
 * The companion object provides a method to get the color by its value.
 */
enum class Color(val value: String) {
    /**
     * This color represents a clothing item that is aqua.
     */
    Aqua("aqua"),

    /**
     * This color represents a clothing item that is beige.
     */
    Beige("beige"),

    /**
     * This color represents a clothing item that is black.
     */
    Black("black"),

    /**
     * This color represents a clothing item that is blue.
     */
    Blue("blue"),

    /**
     * This color represents a clothing item that is brown.
     */
    Brown("brown"),

    /**
     * This color represents a clothing item that is colorful.
     */
    Colorful("colorful"),

    /**
     * This color represents a clothing item that is gray.
     */
    Gray("gray"),

    /**
     * This color represents a clothing item that is green.
     */
    Green("green"),

    /**
     * This color represents a clothing item that is orange.
     */
    Orange("orange"),

    /**
     * This color represents a clothing item that is pink.
     */
    Pink("pink"),

    /**
     * This color represents a clothing item that is purple.
     */
    Purple("purple"),

    /**
     * This color represents a clothing item that is red.
     */
    Red("red"),

    /**
     * This color represents a clothing item that is white.
     */
    White("white"),

    /**
     * This color represents a clothing item that is yellow.
     */
    Yellow("yellow");

    companion object {
        /**
         * This method gets the color by its value.
         *
         * @param value The value of the color.
         * @return The color corresponding to the given value, or null if no match is found.
         */
        fun getByValue(value: String?): Color? {
            return values().find { it.value == value }
        }
    }
}