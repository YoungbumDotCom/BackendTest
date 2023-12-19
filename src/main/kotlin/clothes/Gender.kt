package emiyaj.clothes

/**
 * This enum class represents the gender of a clothing item.
 * Each enum value corresponds to a specific gender.
 *
 * @property value The value related to the gender of the clothing item.
 *
 * The companion object provides a method to get the gender by its value.
 */
enum class Gender(val value: String) {
    /**
     * This gender represents a clothing item that is free for all genders.
     */
    Free("Free"),

    /**
     * This gender represents a clothing item that is specifically for men.
     */
    Manly("Manly"),

    /**
     * This gender represents a clothing item that is specifically for women.
     */
    Womanly("Womanly");

    companion object {
        /**
         * This method gets the gender by its value.
         *
         * @param value The value of the gender.
         * @return The gender corresponding to the given value, or null if no match is found.
         */
        fun getByValue(value: String?): Gender? = values().find { it.value == value}
    }
}