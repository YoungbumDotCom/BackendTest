package emiyaj.clothes.model

import com.beust.klaxon.Json
import kotlinx.serialization.Serializable

/**
 * This data class represents the translations for different languages of a clothing item.
 * Each property corresponds to a specific language translation.
 *
 * @property sourceSheet The source sheet from where the translations are fetched.
 * @property id The unique identifier for the translations.
 * @property version The version of the translations, can be null.
 * @property uSen The translation in US English.
 * @property eUen The translation in EU English.
 * @property eUde The translation in EU German.
 * @property eUes The translation in EU Spanish.
 * @property uSes The translation in US Spanish.
 * @property eUfr The translation in EU French.
 * @property uSfr The translation in US French.
 * @property eUit The translation in EU Italian.
 * @property eUnl The translation in EU Dutch.
 * @property tWzh The translation in Taiwan Chinese.
 * @property cNzh The translation in China Chinese.
 * @property jPja The translation in Japan Japanese.
 * @property kRko The translation in Korea Korean.
 * @property eUru The translation in EU Russian.
 * @property variantID The variant identifier of the clothing item, can be null.
 * @property clothName The name of the clothing item, can be null.
 * @property clothGroup The group identifier of the clothing item, can be null.
 */
@Serializable
data class Translations (
    val sourceSheet: String,
    val id: String,
    val version: String? = null,
    val uSen: String,
    val eUen: String,
    val eUde: String,
    val eUes: String,
    val uSes: String,
    val eUfr: String,
    val uSfr: String,
    val eUit: String,
    val eUnl: String,
    val tWzh: String,
    val cNzh: String,
    val jPja: String,
    val kRko: String,
    val eUru: String,
    //val plural: Boolean? = false,

    @Json(name = "variantId")
    val variantID: Long? = null,

    val clothName: String? = null,
    val clothGroup: Long? = null
)