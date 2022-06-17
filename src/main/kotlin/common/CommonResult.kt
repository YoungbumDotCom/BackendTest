package emiyaj.common

import com.fasterxml.jackson.annotation.JsonIgnore

data class CommonResult(@JsonIgnore val status: CommonStatus, override val comment: String, override val data: Any? = null) : Result {
    override val code: String
        get() = status.statusText
}