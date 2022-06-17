package emiyaj.clothes.model

import com.beust.klaxon.Json
import emiyaj.clothes.Gender
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

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