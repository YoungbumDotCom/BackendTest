package emiyaj.clothes

enum class Type(val value: String) {
    TOP("top"),
    BOTTOM("bottom"),
    DRESS("dress"),
    UMBRELLA("umbrella");

    companion object {
        fun getByValue(value: String?): Type? {
            return values().find { it.value == value }
        }
    }
}