package emiyaj.clothes.model

import emiyaj.clothes.*

data class Clothes (
        val uniqueId: String,
        val name: String,
        val nameKorean: String,
        val variation: String,
        val buy: Int,
        val sell: Int,
        val miles: Int = -1,
        val color1: Color,
        val color2: Color,
        val style1: Style,
        val style2: Style,
        val season: Season,
        val mannequin: Boolean,
    //val themes: Array<String>,
        val catalog: Catalog?,
        val source: Source,
        val internalId: Int,
        val filename: String,
        val type: Type
)