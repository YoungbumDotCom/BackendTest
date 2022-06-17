package emiyaj.clothes.model

import com.beust.klaxon.Json

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