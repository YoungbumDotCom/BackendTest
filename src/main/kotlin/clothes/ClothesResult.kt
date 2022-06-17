package emiyaj.clothes

import com.fasterxml.jackson.annotation.JsonIgnore
import emiyaj.common.Result

data class ClothesResult(@JsonIgnore val status: ClothesStatus, override val comment: String, override val data: Any? = null) : Result {
    override val code: String
        get() = status.code

    companion object {
        fun badRequest(): ClothesResult = ClothesResult(ClothesStatus.BAD_REQUEST, "Bad Request")
    }
}