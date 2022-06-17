package emiyaj.clothes

enum class Style(val value: String) {
    Active("active"),
    Simple("simple"),
    Cool("cool"),
    Cute("cute"),
    Elegant("elegant"),
    Gorgeous("gorgeous"),
    NotImplemented("null");
    companion object {
        fun getByValue(value: String?): Style? {
            return values().find { it.value == value }
        }
    }
}