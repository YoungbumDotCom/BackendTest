package emiyaj.clothes.model

import com.beust.klaxon.Json
import emiyaj.clothes.*
import kotlinx.serialization.Serializable

@Serializable
data class ClothesJson (
    val sourceSheet: String? = null,
    //val sourceSheet: TranslationsSourceSheet
    val name: String,
    val closetImage: String? = null,
    val storageImage: String? = null,
    val variation: String? = null,
    val size: String? = null,
    //val size: Size
    val diy: Boolean = false,
    val buy: Int? = 0,
    val sell: Int? = 0,
    val colors: ArrayList<String> = arrayListOf(),
    val styles: ArrayList<Style> = arrayListOf(),
    val theme: ArrayList<String> = arrayListOf(),
    //val themes: List<Theme>? = null,
    val hhaBasePoints: Long,
    //val sortOrder: Long? = null,
    //val villagerEquippable: Boolean = false,
    //val unlocked: Boolean = true,
    //val seasonalAvailability: String? = null,
    //val seasonalAvailability: SeasonalAvailability
    val seasonality: String? = null,
    //val seasonality: SeasonalAvailability? = null
    //val seasonEvent: String? = null,
    //val seasonEventExclusive: Boolean? = null,
    val translations: Translations,
    val mannequinSeason: String? = null,
    //val gender: Gender? = null,
    //val catalog: String? = null,
    val source: ArrayList<String>? = null,
    //val variations: String? = null,
    //val themesTranslations: ThemesTranslations,
    //val recipe: Any? = null,
    //val mannequinSeason: SeasonalAvailability? = null
    //val gender: Gender?
    //val catalog: Catalog?
    //val source: List<Source>
    //val clothGroupId: String = 0,
    val variations: List<Variation>? = null,
    //val recipe: Recipe?
    //val sourceNotes: List<String>? = null,
    val exchangePrice: Long? = null,
    //val exchangeCurrency: String? = null,
    //val villagerGender: Gender? = null,
    val filename: String? = null,
    //@Json(name = "clothGroupId")
    //val clothGroupID: Long? = null,

    @Json(name = "internalId")
    val internalID: Int? = null,

    @Json(name = "uniqueEntryId")
    val uniqueEntryID: String? = null
    //val versionAdded: Version
)