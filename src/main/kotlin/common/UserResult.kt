package emiyaj.common

import com.fasterxml.jackson.annotation.JsonIgnore
import emiyaj.common.Result
import emiyaj.user.UserStatus

data class UserResult(
        @JsonIgnore val status: UserStatus,
        override val comment: String,
        override val data: Any? = null

) : Result {
    override val code: String
        get() = status.code
}