package emiyaj.common

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * This interface represents a result in the application.
 * It is used as a common structure for responses to client requests.
 * It includes a code, a comment, and optional data.
 * It is annotated with @JsonInclude to specify that null values should not be included in the JSON serialization of objects of this type.
 *
 * @property code The code of the result. This is a unique identifier for each type of result.
 * @property comment The comment of the result. This provides a human-readable description of the result.
 * @property data The data associated with the result. This is optional and defaults to null.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
interface Result {
    val code: String
    val comment: String
    val data: Any?
}