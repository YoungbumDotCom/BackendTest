package emiyaj.clothes.model

import com.beust.klaxon.Json
import kotlinx.serialization.Serializable

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