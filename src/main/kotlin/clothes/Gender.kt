package emiyaj.clothes

enum class Gender(val value: String) {
    Free("Free"),
    Manly("Manly"),
    Womanly("Womanly");

    companion object {
        fun getByValue(value: String?): Gender? = values().find { it.value == value}
    }
}