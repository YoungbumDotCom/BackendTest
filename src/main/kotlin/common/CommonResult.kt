package emiyaj.common

import com.fasterxml.jackson.annotation.JsonIgnore

/**
 * This data class represents a common result in the application.
 * It extends the Result interface and includes a CommonStatus, a comment, and optional data.
 * The CommonStatus is ignored in the JSON serialization of objects of this type.
 *
 * @property status The CommonStatus of the result. This provides a common status code.
 * @property comment The comment of the result. This provides a human-readable description of the result.
 * @property data The data associated with the result. This is optional and defaults to null.
 */
data class CommonResult(
    @JsonIgnore val status: CommonStatus,
    override val comment: String,
    override val data: Any? = null
) : Result {
    /**
     * This property overrides the code property in the Result interface.
     * It retrieves the status text from the CommonStatus of the result.
     */
    override val code: String
        get() = status.statusText
}