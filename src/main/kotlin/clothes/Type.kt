package emiyaj.clothes

/**
 * This enum class represents the type of a cloth item in the clothes database.
 * It has four possible values: TOP, BOTTOM, DRESS, and UMBRELLA.
 * The value of each enum constant is a string that represents the type in the database.
 */
enum class Type(val value: String) {
    TOP("top"), // Represents a cloth item that is a top
    BOTTOM("bottom"), // Represents a cloth item that is a bottom
    DRESS("dress"), // Represents a cloth item that is a dress
    UMBRELLA("umbrella"); // Represents a cloth item that is an umbrella

    /**
     * This companion object provides a function to get an enum constant by its value.
     */
    companion object {
        /**
         * This function returns the enum constant that matches the provided value.
         * If no matching enum constant is found, it returns null.
         *
         * @param value The value of the enum constant to find.
         * @return The matching enum constant, or null if no match is found.
         */
        fun getByValue(value: String?): Type? {
            // Iterate over the enum constants
            // Return the first one that matches the provided value
            return values().find { it.value == value }
        }
    }
}