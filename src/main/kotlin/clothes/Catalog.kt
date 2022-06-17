package emiyaj.clothes

enum class Catalog(val value: String) {
    SALE("sale"),
    NOT_FOR_SALE("notsale"),
    NOT_IN_CATALOG("no");

    companion object {
        fun getByValue(value: String?): Catalog? {
            return values().find { it.value == value }
        }
    }
}