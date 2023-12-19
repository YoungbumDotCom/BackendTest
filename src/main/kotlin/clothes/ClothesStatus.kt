package emiyaj.clothes

/**
 * This enum class represents the status of a clothing operation.
 * Each enum value corresponds to a specific status of the operation.
 *
 * @property code The code related to the status of the operation.
 */
enum class ClothesStatus(val code: String) {
    /**
     * This status represents a successful operation.
     */
    SUCCESS("clothes00"),

    /**
     * This status represents a bad request operation.
     */
    BAD_REQUEST("clothes01"),

    /**
     * This status represents a not found operation.
     */
    NOT_FOUND("clothes02")
}