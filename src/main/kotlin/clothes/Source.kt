package emiyaj.clothes

enum class Source(val value: String) {
    ABLE_SISTERS("able"),
    BUG("bug"),
    INTERNATIONAL_CHILDREN_S_DAY("childrensday"),
    NOOK_SHOPPING_PROMOTION("shoppingpromotion"),
    NOOK_SHOPPING_CATALOG("shoppingcatalog"),
    NOOK_MILES_SHOP("milesshop"),
    CRAFTING("crafting"),
    RECYCLE_BIN("recyclebin"),
    DODO_AIRLINES("dodo"),
    MOM("mom"),
    LABELLE("labelle"),
    FISHING_TOURNEY("fishing"),
    WEDDING("wedding"),
    WINTER_SOLSTICE_FESTIVAL("winterfestival");

    companion object {
        fun getByValue(value: String?): Source? {
            return values().find { it.value == value }
        }
    }
}