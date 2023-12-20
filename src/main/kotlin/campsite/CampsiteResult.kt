package emiyaj.campsite

import com.fasterxml.jackson.annotation.JsonIgnore
import emiyaj.common.CommonStatus
import emiyaj.common.Result
import emiyaj.util.AESEncryption

/**
 * This is a data class that represents the result of a campsite-related operation.
 * It implements the Result interface and provides additional functionality specific to the campsite module.
 *
 * @property status The status of the operation. This is not included in the JSON representation of the object.
 * @property comment A comment about the operation.
 * @property data The data resulting from the operation. This can be any object, but is usually a string or a list of CampsiteContent objects.
 */
data class CampsiteResult (
        @JsonIgnore val status: CommonStatus,
        override val comment: String,
        override val data: Any? = null
) : Result {
    /**
     * The status code of the operation. This is derived from the status property.
     */
    override val code: String
        get() = status.statusText

    companion object {
        /**
         * This method creates a CampsiteResult object for a bad request.
         *
         * @return A CampsiteResult object with a status of UNAUTHORIZED and a comment of "Bad Request".
         */
        fun badRequest(): CampsiteResult = CampsiteResult(CommonStatus.UNAUTHORIZED, "Bad Request")

        /**
         * This method creates a CampsiteResult object for a successful operation, with the data encrypted.
         *
         * @param data The data resulting from the operation. This is converted to a JSON string and encrypted.
         * @return A CampsiteResult object with a status of NORMAL, a comment of "success", and the encrypted data.
         */
        fun successWithEncrypt(data: String): CampsiteResult {
            return CampsiteResult(CommonStatus.NORMAL, "success", AESEncryption.encrypt(data))
        }
    }
}