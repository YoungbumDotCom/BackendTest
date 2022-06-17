package emiyaj.friendshippoint

import com.fasterxml.jackson.annotation.JsonIgnore
import emiyaj.common.Result
import emiyaj.util.AESEncryption

data class FriendshipPointsResult(@JsonIgnore val status: FriendshipPointsStatus, override val comment: String, override val data: Any? = null) : Result {
    override val code: String
        get() = status.code

    companion object {
        fun success(): FriendshipPointsResult {
            return FriendshipPointsResult(FriendshipPointsStatus.SUCCESS, comment = "success")
        }

        fun successWithDataAndEncrypt(data: String): FriendshipPointsResult {
            return FriendshipPointsResult(FriendshipPointsStatus.SUCCESS, "success", AESEncryption.encrypt(data))
        }
        fun badRequest(): FriendshipPointsResult = FriendshipPointsResult(FriendshipPointsStatus.BAD_REQUEST, "Bad Request")
    }
}