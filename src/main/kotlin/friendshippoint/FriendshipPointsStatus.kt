package emiyaj.friendshippoint

/**
 * This is an enum class that represents the status of a friendship points-related operation.
 * Each enum constant is associated with a status code.
 *
 * @property code The status code associated with the enum constant.
 */
enum class FriendshipPointsStatus(val code: String) {
    /**
     * This constant represents a successful operation.
     * The associated status code is "fpoints00".
     */
    SUCCESS("fpoints00"),

    /**
     * This constant represents a bad request.
     * The associated status code is "fpoints01".
     */
    BAD_REQUEST("fpoints01")
}