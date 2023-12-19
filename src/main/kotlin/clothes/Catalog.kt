package emiyaj.clothes

/**
 * This enum class represents the catalog status of a cloth item in the clothes database.
 * It has three possible values: SALE, NOT_FOR_SALE, and NOT_IN_CATALOG.
 * The value of each enum constant is a string that represents the catalog status in the database.
 */
enum class Catalog(val value: String) {
    SALE("sale"), // Represents a cloth item that is for sale
    NOT_FOR_SALE("notsale"), // Represents a cloth item that is not for sale
    NOT_IN_CATALOG("no"); // Represents a cloth item that is not in the catalog

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
        fun getByValue(value: String?): Catalog? {
            // Iterate over the enum constants
            // Return the first one that matches the provided value
            return values().find { it.value == value }
        }
    }
}