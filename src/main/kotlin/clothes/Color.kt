package emiyaj.clothes

enum class Color(val value: String) {
    Aqua("aqua"),
    Beige("beige"),
    Black("black"),
    Blue("blue"),
    Brown("brown"),
    Colorful("colorful"),
    Gray("gray"),
    Green("green"),
    Orange("orange"),
    Pink("pink"),
    Purple("purple"),
    Red("red"),
    White("white"),
    Yellow("yellow");

    companion object {
        fun getByValue(value: String?): Color? {
            return values().find { it.value == value }
        }
    }
}