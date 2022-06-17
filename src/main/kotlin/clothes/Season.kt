package emiyaj.clothes

enum class Season(val value: String) {
    ALL("all"),
    SUMMER("summer"),
    WINTER("winter");

    companion object {
        fun getByValue(value: String?): Season? {
            return values().find { it.value == value }
        }
    }
}