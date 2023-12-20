package emiyaj.friendshippoint

import com.fasterxml.jackson.annotation.JsonIgnore
import emiyaj.common.Result
import emiyaj.util.AESEncryption

/**
 * This is a data class that represents the result of a friendship points-related operation.
 * It implements the Result interface and provides additional functionality specific to the friendship points module.
 *
 * @property status The status of the operation. This is not included in the JSON representation of the object.
 * @property comment A comment about the operation.
 * @property data The data resulting from the operation. This can be any object, but is usually a string or a list of FriendshipPoints objects.
 */
data class FriendshipPointsResult(@JsonIgnore val status: FriendshipPointsStatus, override val comment: String, override val data: Any? = null) : Result {
    /**
     * The status code of the operation. This is derived from the status property.
     */
    override val code: String
        get() = status.code

    companion object {
        /**
         * This method creates a FriendshipPointsResult object for a successful operation.
         *
         * @return A FriendshipPointsResult object with a status of SUCCESS and a comment of "success".
         */
        fun success(): FriendshipPointsResult {
            return FriendshipPointsResult(FriendshipPointsStatus.SUCCESS, comment = "success")
        }

        /**
         * This method creates a FriendshipPointsResult object for a successful operation, with the data encrypted.
         *
         * @param data The data resulting from the operation. This is converted to a JSON string and encrypted.
         * @return A FriendshipPointsResult object with a status of SUCCESS, a comment of "success", and the encrypted data.
         */
        fun successWithDataAndEncrypt(data: String): FriendshipPointsResult {
            return FriendshipPointsResult(FriendshipPointsStatus.SUCCESS, "success", AESEncryption.encrypt(data))
        }

        /**
         * This method creates a FriendshipPointsResult object for a bad request.
         *
         * @return A FriendshipPointsResult object with a status of BAD_REQUEST and a comment of "Bad Request".
         */
        fun badRequest(): FriendshipPointsResult = FriendshipPointsResult(FriendshipPointsStatus.BAD_REQUEST, "Bad Request")
    }
}