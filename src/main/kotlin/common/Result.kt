package emiyaj.common

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
interface Result {
        val code: String
        val comment: String
        val data: Any?
}
