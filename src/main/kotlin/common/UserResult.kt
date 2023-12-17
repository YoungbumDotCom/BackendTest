package emiyaj.common

import com.fasterxml.jackson.annotation.JsonIgnore
import emiyaj.common.Result
import emiyaj.user.UserStatus

/**
 * This data class represents a user-specific result in the application.
 * It extends the Result interface and includes a UserStatus, a comment, and optional data.
 * The UserStatus is ignored in the JSON serialization of objects of this type.
 *
 * @property status The UserStatus of the result. This provides a user-specific status code.
 * @property comment The comment of the result. This provides a human-readable description of the result.
 * @property data The data associated with the result. This is optional and defaults to null.
 */
data class UserResult(
        @JsonIgnore val status: UserStatus,
        override val comment: String,
        override val data: Any? = null

) : Result {
    /**
     * This property overrides the code property in the Result interface.
     * It retrieves the code from the UserStatus of the result.
     */
    override val code: String
        get() = status.code
}