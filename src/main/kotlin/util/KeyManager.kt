package emiyaj.util

import java.security.SecureRandom
import java.util.*
import javax.crypto.KeyGenerator
import javax.crypto.spec.IvParameterSpec

/**
 * This class provides utility functions for managing AES keys and initialization vectors (IVs).
 * It is implemented as a companion object, so its methods can be called without creating an instance of the class.
 */
class KeyManager {
    companion object {
        // Default AES key and IV retrieved from environment variables
        val defaultKey = EnvVariable.getVariable("aes_key") ?: ""
        val defaultIv = EnvVariable.getVariable("aes_iv") ?: ""
        // Generated AES key and IV for tokens
        val tokenKey = generateAESKey(128)
        val tokenIv = generateAESIv(128)

        /**
         * This function generates an AES key of a given size.
         * It uses the KeyGenerator class to generate a secret key, which is then encoded to a byte array and converted to a Base64 string.
         *
         * @param size The size of the key to be generated. Default is 256.
         * @return The generated AES key as a Base64 string.
         */
        fun generateAESKey(size: Int = 256): String {
            val keyGenerator = KeyGenerator.getInstance("AES")
            val secureRandom = SecureRandom()
            keyGenerator.init(size, secureRandom)
            return Base64.getEncoder().encodeToString(keyGenerator.generateKey().encoded)
        }

        /**
         * This function generates an AES initialization vector (IV) of a given size.
         * It uses the SecureRandom class to generate a random byte array, which is then used to create an IvParameterSpec.
         * The IV of the IvParameterSpec is then converted to a Base64 string.
         *
         * @param size The size of the IV to be generated. Default is 256.
         * @return The generated AES IV as a Base64 string.
         */
        fun generateAESIv(size: Int = 256): String {
            val secureRandom = SecureRandom()
            val iv = ByteArray(size / 8)
            secureRandom.nextBytes(iv)
            val ivSpec = IvParameterSpec(iv)
            return Base64.getEncoder().encodeToString(ivSpec.iv)
        }
    }
}