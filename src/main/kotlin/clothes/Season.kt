package emiyaj.clothes

/**
 * This enum class represents the season of a clothing item.
 * Each enum value corresponds to a specific season.
 *
 * @property value The value related to the season of the clothing item.
 *
 * The companion object provides a method to get the season by its value.
 */
enum class Season(val value: String) {
    /**
     * This season represents a clothing item that is suitable for all seasons.
     */
    ALL("all"),

    /**
     * This season represents a clothing item that is suitable for summer.
     */
    SUMMER("summer"),

    /**
     * This season represents a clothing item that is suitable for winter.
     */
    WINTER("winter");

    companion object {
        /**
         * This method gets the season by its value.
         *
         * @param value The value of the season.
         * @return The season corresponding to the given value, or null if no match is found.
         */
        fun getByValue(value: String?): Season? {
            return values().find { it.value == value }
        }
    }
}