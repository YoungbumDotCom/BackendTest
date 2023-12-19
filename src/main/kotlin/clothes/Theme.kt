package emiyaj.clothes

/**
 * This enum class represents the theme of a clothing item.
 * Each enum value corresponds to a specific theme.
 *
 * @property value The value related to the theme of the clothing item.
 *
 * The companion object provides a method to get the theme by its value.
 */
enum class Theme(val value: String) {
    /**
     * This theme represents a clothing item that is comfy.
     */
    comfy("comfy"),

    /**
     * This theme represents a clothing item that is suitable for everyday use.
     */
    everyday("everyday"),

    /**
     * This theme represents a clothing item that is related to fairy tales.
     */
    fairytale("fairy tale"),

    /**
     * This theme represents a clothing item that is formal.
     */
    formal("formal"),

    /**
     * This theme represents a clothing item that is goth.
     */
    goth("goth"),

    /**
     * This theme represents a clothing item that is outdoorsy.
     */
    outdoorsy("outdoorsy"),

    /**
     * This theme represents a clothing item that is suitable for parties.
     */
    party("party"),

    /**
     * This theme represents a clothing item that is sporty.
     */
    sporty("sporty"),

    /**
     * This theme represents a clothing item that is theatrical.
     */
    theatrical("theatrical"),

    /**
     * This theme represents a clothing item that is vacations.
     */
    vacation("vacation"),

    /**
     * This theme represents a clothing item that is work.
     */
    work("work");

    companion object {
        /**
         * This method gets the theme by its value.
         *
         * @param value The value of the theme.
         * @return The theme corresponding to the given value, or null if no match is found.
         */
        fun getByValue(value: String?): Theme? {
            return values().find { it.value == value }
        }
    }
}