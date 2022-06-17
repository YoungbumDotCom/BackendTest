package emiyaj.util

import java.security.SecureRandom
import java.util.*
import javax.crypto.KeyGenerator
import javax.crypto.spec.IvParameterSpec

class KeyManager {
    companion object {
        val defaultKey = EnvVariable.getVariable("aes_key") ?: ""
        val defaultIv = EnvVariable.getVariable("aes_iv") ?: ""
        val tokenKey = generateAESKey(128)
        val tokenIv = generateAESIv(128)

        fun generateAESKey(size: Int = 256): String {
            val keyGenerator = KeyGenerator.getInstance("AES")
            val secureRandom = SecureRandom()
            keyGenerator.init(size, secureRandom)
            return Base64.getEncoder().encodeToString(keyGenerator.generateKey().encoded)
        }

        fun generateAESIv(size: Int = 256): String {
            val secureRandom = SecureRandom()
            val iv = ByteArray(size / 8)
            secureRandom.nextBytes(iv)
            val ivSpec = IvParameterSpec(iv)
            return Base64.getEncoder().encodeToString(ivSpec.iv)
        }
    }
}