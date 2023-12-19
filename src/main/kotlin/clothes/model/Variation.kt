package emiyaj.clothes.model

import com.beust.klaxon.Json
import emiyaj.clothes.Gender
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * This data class represents a Variation of a cloth item in the clothes database.
 * It includes various properties of the cloth item such as its images, variation name, gender, sort order, filename, cloth group id, internal id, unique entry id, and colors.
 * The class is marked as Serializable, which means it can be serialized to and deserialized from a JSON representation.
 *
 * @property closetImage The image of the cloth item when it is in the closet.
 * @property storageImage The image of the cloth item when it is in storage.
 * @property variation The name of the variation.
 * @property gender The gender that the cloth item is suitable for. It is of type Gender, which is an enum class.
 * @property villagerGender The gender of the villager that the cloth item is suitable for. It is of type Gender, which is an enum class.
 * @property sortOrder The sort order of the cloth item. It is used to sort the cloth items in a list.
 * @property filename The filename of the cloth item's image.
 * @property clothGroupId The group id of the cloth item. Cloth items that belong to the same group are variations of the same item.
 * @property internalId The internal id of the cloth item. It is unique for each cloth item.
 * @property uniqueEntryId The unique entry id of the cloth item. It is unique for each cloth item.
 * @property colors The colors of the cloth item. It is a list of strings, each representing a color.
 */
@Serializable
data class Variation (
    val closetImage: String,
    val storageImage: String,
    @Contextual val variation: String,
    //val exchangePrice: Any? = null,
    //val exchangeCurrency: Any? = null,
    //val seasonEvent: String,
    //val seasonEventExclusive: Boolean,
    //val seasonality: String,
    //val mannequinSeason: Any? = null,
    val gender: Gender = Gender.Free,
    val villagerGender: Gender = Gender.Free,
    val sortOrder: Long = 1,
    val filename: String? = null,

    @Json(name = "clothGroupId")
    val clothGroupId: Long,

    @Json(name = "internalId")
    val internalId: Long,

    @Json(name = "uniqueEntryId")
    val uniqueEntryId: String,

    //val variantTranslations: Translations,
    val colors: List<String>
)