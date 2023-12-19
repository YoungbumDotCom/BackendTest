package emiyaj.clothes

/**
 * This enum class represents the source of a clothing item.
 * Each enum value corresponds to a specific source.
 *
 * @property value The value related to the source of the clothing item.
 *
 * The companion object provides a method to get the source by its value.
 */
enum class Source(val value: String) {
    /**
     * This source represents a clothing item that is obtained from Able Sisters.
     */
    ABLE_SISTERS("able"),

    /**
     * This source represents a clothing item that is obtained from bugs.
     */
    BUG("bug"),

    /**
     * This source represents a clothing item that is obtained from International Children's Day.
     */
    INTERNATIONAL_CHILDREN_S_DAY("childrensday"),

    /**
     * This source represents a clothing item that is obtained from Nook Shopping Promotion.
     */
    NOOK_SHOPPING_PROMOTION("shoppingpromotion"),

    /**
     * This source represents a clothing item that is obtained from Nook Shopping Catalog.
     */
    NOOK_SHOPPING_CATALOG("shoppingcatalog"),

    /**
     * This source represents a clothing item that is obtained from Nook Miles Shop.
     */
    NOOK_MILES_SHOP("milesshop"),

    /**
     * This source represents a clothing item that is obtained from crafting.
     */
    CRAFTING("crafting"),

    /**
     * This source represents a clothing item that is obtained from Recycle Bin.
     */
    RECYCLE_BIN("recyclebin"),

    /**
     * This source represents a clothing item that is obtained from Dodo Airlines.
     */
    DODO_AIRLINES("dodo"),

    /**
     * This source represents a clothing item that is obtained from Mom.
     */
    MOM("mom"),

    /**
     * This source represents a clothing item that is obtained from Labelle.
     */
    LABELLE("labelle"),

    /**
     * This source represents a clothing item that is obtained from Fishing Tourney.
     */
    FISHING_TOURNEY("fishing"),

    /**
     * This source represents a clothing item that is obtained from Wedding.
     */
    WEDDING("wedding"),

    /**
     * This source represents a clothing item that is obtained from Winter Solstice Festival.
     */
    WINTER_SOLSTICE_FESTIVAL("winterfestival");

    companion object {
        /**
         * This method gets the source by its value.
         *
         * @param value The value of the source.
         * @return The source corresponding to the given value, or null if no match is found.
         */
        fun getByValue(value: String?): Source? {
            return values().find { it.value == value }
        }
    }
}