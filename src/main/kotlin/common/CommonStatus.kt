package emiyaj.common

/**
 * This enum class represents the status of a common operation in the application.
 * Each status has a status text associated with it.
 *
 * @property statusText The status text of the common status. This is a unique identifier for each status.
 */
enum class CommonStatus(val statusText: String) {
    /**
     * The NORMAL status represents a successful operation.
     */
    NORMAL("emiyaj00"),

    /**
     * The WARNING status represents an operation that completed with warnings.
     */
    WARNING("emiyaj01"),

    /**
     * The CRITICAL status represents a critical failure in an operation.
     */
    CRITICAL("emiyaj02"),

    /**
     * The NOT_OPERATIONAL status represents an operation that could not be performed because the system or a component is not operational.
     */
    NOT_OPERATIONAL("emiyaj03"),

    /**
     * The INTERNAL_ERROR status represents an internal error that cannot be shown to the public.
     */
    INTERNAL_ERROR("emiyaj04"),

    /**
     * The UNAUTHORIZED status represents an operation that failed due to lack of authorization.
     */
    UNAUTHORIZED("emiyaj05")
}