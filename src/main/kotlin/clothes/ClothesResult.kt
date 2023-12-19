package emiyaj.clothes

import com.fasterxml.jackson.annotation.JsonIgnore
import emiyaj.common.Result

/**
 * This data class represents the result of a clothing operation.
 * Each property corresponds to a specific attribute of the result.
 *
 * @property status The status of the clothing operation.
 * @property comment The comment related to the result of the operation.
 * @property data The data related to the result of the operation, can be null.
 * @property code The code related to the status of the operation.
 *
 * The companion object provides a method to create a bad request result.
 */
data class ClothesResult(@JsonIgnore val status: ClothesStatus, override val comment: String, override val data: Any? = null) : Result {
    override val code: String
        get() = status.code

    companion object {
        /**
         * This method creates a bad request result.
         *
         * @return A ClothesResult object with BAD_REQUEST status and "Bad Request" comment.
         */
        fun badRequest(): ClothesResult = ClothesResult(ClothesStatus.BAD_REQUEST, "Bad Request")
    }
}