package emiyaj.clothes

enum class Theme(val value: String) {
    comfy("comfy"),
    everyday("everyday"),
    fairytale("fairy tale"),
    formal("formal"),
    goth("goth"),
    outdoorsy("outdoorsy"),
    party("party"),
    sporty("sporty"),
    theatrical("theatrical"),
    vacation("vacation"),
    work("work");

    companion object {
        fun getByValue(value: String?): Theme? {
            return values().find { it.value == value }
        }
    }
}