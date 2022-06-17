package emiyaj.util

import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class PBKDF2 {
    companion object {
        private const val iterations = 25000
        private const val keyLength = 512 * 8

        private fun ByteArray.toHexString() = joinToString("") { "%02x".format(it) }

        fun hash(raw: String, salt: String): String {
            val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
            val spec = PBEKeySpec(raw.toCharArray(), salt.toByteArray(), iterations, keyLength)
            val key = skf.generateSecret(spec)
            val res = key.encoded
            return res.toHexString()
        }
    }
}