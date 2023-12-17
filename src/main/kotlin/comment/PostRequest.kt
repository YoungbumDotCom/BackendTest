package emiyaj.comment

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import emiyaj.util.AESEncryption

/**
 * This data class represents a post request in the application.
 * It includes a nickname, data, and comment properties which are received in the request.
 * It also includes a code property which is the decrypted data.
 *
 * @property nickname The nickname of the user posting the comment.
 * @property data The encrypted data received in the request. This is typically the code of the villager for whom the comment is being posted.
 * @property comment The text of the comment being posted.
 * @property code The decrypted data. It is decrypted using the AESEncryption utility. This is typically the code of the villager for whom the comment is being posted.
 *
 * @constructor Creates a new PostRequest object.
 * It is annotated with @JsonCreator to specify that it should be used for JSON deserialization.
 * The nickname, data, and comment parameters are annotated with @JsonProperty to specify that they should be used for JSON property binding.
 *
 * @param nickname The nickname of the user posting the comment.
 * @param data The encrypted data received in the request.
 * @param comment The text of the comment being posted.
 */
data class PostRequest @JsonCreator constructor(
    @JsonProperty val nickname: String,
    @JsonProperty val data: String,
    @JsonProperty val comment: String
) {
    val code = AESEncryption.decrypt(data)
}