package emiyaj.common

enum class CommonStatus(val statusText: String) {
    NORMAL("emiyaj00"),
    WARNING("emiyaj01"),
    CRITICAL("emiyaj02"),
    NOT_OPERATIONAL("emiyaj03"),
    INTERNAL_ERROR("emiyaj04"), // Error that cannot show to public.
    UNAUTHORIZED("emiyaj05")
}