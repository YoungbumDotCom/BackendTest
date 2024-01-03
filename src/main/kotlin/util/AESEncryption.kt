package emiyaj.util

import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AESEncryption(private val key: ByteArray = generateRandomKey()) {
    val iv = IvParameterSpec(key, 0, 16)
    val secretKey = SecretKeySpec(key, "AES")

    fun encrypt(raw: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)

        val encrypted = cipher.doFinal(raw.toByteArray())
        return Base64.getEncoder().encodeToString(encrypted)
    }

    fun decrypt(encrypted: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)

        val decrypted = cipher.doFinal(Base64.getDecoder().decode(encrypted))
        return String(decrypted)
    }

    companion object {
        fun generateRandomKey(): ByteArray {
            val key = ByteArray(32)
            val random = SecureRandom()
            random.nextBytes(key)
            return key
        }
    }

}