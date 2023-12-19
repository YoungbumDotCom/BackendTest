package emiyaj.clothes.model

import com.beust.klaxon.Json

/**
 * This data class represents the translations of the themes of a cloth item in the clothes database.
 * It includes properties for each possible theme, each of which is of type Comfy.
 * The class is marked as Serializable, which means it can be serialized to and deserialized from a JSON representation.
 *
 * @property everyday The translation of the 'everyday' theme.
 * @property comfy The translation of the 'comfy' theme.
 * @property outdoorsy The translation of the 'outdoorsy' theme.
 * @property vacation The translation of the 'vacation' theme.
 * @property work The translation of the 'work' theme.
 * @property formal The translation of the 'formal' theme.
 * @property party The translation of the 'party' theme.
 * @property theatrical The translation of the 'theatrical' theme.
 * @property fairyTale The translation of the 'fairy tale' theme.
 * @property sporty The translation of the 'sporty' theme.
 * @property goth The translation of the 'goth' theme.
 */
data class ThemesTranslations (
    val everyday: Comfy? = null,
    val comfy: Comfy? = null,
    val outdoorsy: Comfy? = null,
    val vacation: Comfy? = null,
    val work: Comfy? = null,
    val formal: Comfy? = null,
    val party: Comfy? = null,
    val theatrical: Comfy? = null,

    @Json(name = "fairy tale")
    val fairyTale: Comfy? = null,

    val sporty: Comfy? = null,
    val goth: Comfy? = null
)