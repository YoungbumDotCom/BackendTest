package emiyaj.user

enum class UserStatus(val code: String, val comment: String, val httpCode: Int) {
    AUTHORIZED("user00", "Authorized", 200),
    INVALID("user01", "Invalid", 403),
    EXPIRED("user02", "Expired", 200),
    NOT_ALLOWED("user03", "Not Allowed", 400),
    CREATED("user04", "Created", 200)
}