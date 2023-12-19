package emiyaj.clothes

/**
 * This enum class represents the method of searching for a clothing item.
 * Each enum value corresponds to a specific search method.
 *
 * @property param The parameter related to the search method.
 */
enum class SearchMethod(val param: String) {
    /**
     * This search method represents searching a clothing item by its name.
     */
    NAME("name"),

    /**
     * This search method represents searching a clothing item by its Korean name.
     */
    KOREAN("kor"),

    /**
     * This search method represents searching a clothing item by its style.
     */
    STYLE("style"),

    /**
     * This search method represents searching a clothing item by its color.
     */
    COLOR("color")
}