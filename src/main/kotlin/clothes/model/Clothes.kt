package emiyaj.clothes.model

import emiyaj.clothes.*

/**
 * This data class represents a Clothes item in the clothes database.
 * It includes various properties of the cloth item such as its unique id, name, Korean name, variation, buy price, sell price, miles price, colors, styles, season, mannequin status, catalog status, source, internal id, filename, and type.
 * The class is marked as Serializable, which means it can be serialized to and deserialized from a JSON representation.
 *
 * @property uniqueId The unique id of the cloth item. It is unique for each cloth item.
 * @property name The name of the cloth item.
 * @property nameKorean The Korean name of the cloth item.
 * @property variation The variation of the cloth item.
 * @property buy The buy price of the cloth item.
 * @property sell The sell price of the cloth item.
 * @property miles The miles price of the cloth item. It is -1 if the cloth item cannot be bought with miles.
 * @property color1 The first color of the cloth item. It is of type Color, which is an enum class.
 * @property color2 The second color of the cloth item. It is of type Color, which is an enum class.
 * @property style1 The first style of the cloth item. It is of type Style, which is an enum class.
 * @property style2 The second style of the cloth item. It is of type Style, which is an enum class.
 * @property season The season of the cloth item. It is of type Season, which is an enum class.
 * @property mannequin The mannequin status of the cloth item. It is true if the cloth item can be displayed on a mannequin, otherwise false.
 * @property catalog The catalog status of the cloth item. It is of type Catalog, which is an enum class.
 * @property source The source of the cloth item. It is of type Source, which is an enum class.
 * @property internalId The internal id of the cloth item. It is unique for each cloth item.
 * @property filename The filename of the cloth item's image.
 * @property type The type of the cloth item. It is of type Type, which is an enum class.
 */
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