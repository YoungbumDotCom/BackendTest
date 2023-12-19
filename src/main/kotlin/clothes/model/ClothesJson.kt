package emiyaj.clothes.model

import com.beust.klaxon.Json
import emiyaj.clothes.*
import kotlinx.serialization.Serializable

/**
 * This data class represents the JSON structure of a clothing item in the remote JSON.
 * Each property corresponds to a specific attribute of the clothing item.
 *
 * @property sourceSheet The source sheet from where the clothing item details are fetched, can be null.
 * @property name The name of the clothing item.
 * @property closetImage The image of the clothing item in the closet, can be null.
 * @property storageImage The image of the clothing item in the storage, can be null.
 * @property variation The variation of the clothing item, can be null.
 * @property size The size of the clothing item, can be null.
 * @property diy A boolean indicating whether the clothing item is a DIY item.
 * @property buy The buying price of the clothing item, can be null.
 * @property sell The selling price of the clothing item, can be null.
 * @property colors The list of colors of the clothing item.
 * @property styles The list of styles of the clothing item.
 * @property theme The list of themes of the clothing item.
 * @property hhaBasePoints The base points of the clothing item in the Happy Home Academy.
 * @property seasonality The seasonality of the clothing item, can be null.
 * @property translations The translations of the clothing item.
 * @property mannequinSeason The season of the mannequin, can be null.
 * @property source The list of sources of the clothing item, can be null.
 * @property variations The list of variations of the clothing item, can be null.
 * @property exchangePrice The exchange price of the clothing item, can be null.
 * @property filename The filename of the clothing item, can be null.
 * @property internalID The internal ID of the clothing item, can be null.
 * @property uniqueEntryID The unique entry ID of the clothing item, can be null.
 */
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