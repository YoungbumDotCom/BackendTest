package emiyaj.clothes.model

/**
 * This data class represents the everyday clothing items.
 * Each property corresponds to a specific attribute of the clothing item.
 *
 * @property sourceSheet The source sheet from where the clothing item details are fetched.
 * @property id The unique identifier for the clothing item.
 * @property version The version of the clothing item.
 * @property english The name of the clothing item in English.
 * @property englishEurope The name of the clothing item in European English.
 * @property german The name of the clothing item in German.
 * @property spanish The name of the clothing item in Spanish.
 * @property spanishUs The name of the clothing item in US Spanish.
 * @property french The name of the clothing item in French.
 * @property frenchUs The name of the clothing item in US French.
 * @property italian The name of the clothing item in Italian.
 * @property dutch The name of the clothing item in Dutch.
 * @property chinese The name of the clothing item in Chinese.
 * @property chineseTraditional The name of the clothing item in Traditional Chinese.
 * @property japanese The name of the clothing item in Japanese.
 * @property korean The name of the clothing item in Korean.
 * @property russian The name of the clothing item in Russian.
 * @property plural A boolean indicating whether the clothing item is plural.
 */
data class Everyday (
    val sourceSheet: String,
    val id: String,
    val version: String,
    val english: String,
    val englishEurope: String,
    val german: String,
    val spanish: String,
    val spanishUs: String,
    val french: String,
    val frenchUs: String,
    val italian: String,
    val dutch: String,
    val chinese: String,
    val chineseTraditional: String,
    val japanese: String,
    val korean: String,
    val russian: String,
    val plural: Boolean
)