package emiyaj.clothes

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import emiyaj.util.AESEncryption

/**
 * This data class represents the request for searching a clothing item by villager.
 * The request data is encrypted and will be decrypted upon creation of the object.
 *
 * @property data The encrypted data of the request.
 * @property target The target villager for the search.
 * @property index The index of the search result, can be null.
 * @property decryptedData The decrypted data of the request.
 */
data class SearchByVillagerRequest @JsonCreator constructor(
    @JsonProperty val data: String,
    @JsonProperty val target: Int,
    @JsonProperty val index: Int?
) {
    val decryptedData = AESEncryption.decrypt(data)
}