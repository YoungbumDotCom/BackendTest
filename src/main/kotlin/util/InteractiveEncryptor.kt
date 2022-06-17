package emiyaj.util

fun main(args: Array<String>) {
    while(true) {
        println("Type e to encrypt string or d to decrypt to string")
        val code = readLine()
        when (code) {
            "e", "d" -> {
                println("input: ")
                val input = readLine()
                if (input != null) {
                    println("Result: \n${if (code == "e") AESEncryption.encrypt(input) else AESEncryption.decrypt(input)}")
                }
            }
            else -> println("Wrong.")
        }
    }
}
