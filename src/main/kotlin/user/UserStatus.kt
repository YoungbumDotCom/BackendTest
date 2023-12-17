package emiyaj.user

/**
 * This enum class represents the status of a user in the application.
 * Each status has a code, a comment, and an HTTP code associated with it.
 *
 * @property code The code of the user status. This is a unique identifier for each status.
 * @property comment The comment of the user status. This provides a human-readable description of the status.
 * @property httpCode The HTTP code associated with the user status. This is used in HTTP responses.
 */
enum class UserStatus(val code: String, val comment: String, val httpCode: Int) {
    /**
     * The AUTHORIZED status represents a user who is authorized to perform an action.
     * It has an HTTP code of 200, indicating a successful HTTP request.
     */
    AUTHORIZED("user00", "Authorized", 200),

    /**
     * The INVALID status represents a user who is not valid, for example due to invalid credentials.
     * It has an HTTP code of 403, indicating a forbidden HTTP request.
     */
    INVALID("user01", "Invalid", 403),

    /**
     * The EXPIRED status represents a user whose session or token has expired.
     * It has an HTTP code of 200, indicating a successful HTTP request. The actual handling of this status is done in the application logic.
     */
    EXPIRED("user02", "Expired", 200),

    /**
     * The NOT_ALLOWED status represents a user who is not allowed to perform an action due to lack of permissions.
     * It has an HTTP code of 400, indicating a bad HTTP request.
     */
    NOT_ALLOWED("user03", "Not Allowed", 400),

    /**
     * The CREATED status represents a user who has just been created.
     * It has an HTTP code of 200, indicating a successful HTTP request.
     */
    CREATED("user04", "Created", 200)
}