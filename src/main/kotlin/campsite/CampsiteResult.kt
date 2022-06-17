package emiyaj.campsite

import com.fasterxml.jackson.annotation.JsonIgnore
import emiyaj.common.CommonStatus
import emiyaj.common.Result
import emiyaj.util.AESEncryption

data class CampsiteResult (
        @JsonIgnore val status: CommonStatus,
        override val comment: String,
        override val data: Any? = null
) : Result {
    override val code: String
        get() = status.statusText

    companion object {
        fun badRequest(): CampsiteResult = CampsiteResult(CommonStatus.UNAUTHORIZED, "Bad Request")

        fun successWithEncrypt(data: String): CampsiteResult {
            return CampsiteResult(CommonStatus.NORMAL, "success", AESEncryption.encrypt(data))
        }
    }
}