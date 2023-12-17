package emiyaj.util

import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * This class provides a utility for hashing a string using the PBKDF2 with Hmac SHA256 algorithm.
 */
class PBKDF2 {
    companion object {
        // Number of iterations for the PBKDF2 algorithm
        private const val iterations = 25000
        // Length of the generated key
        private const val keyLength = 512 * 8

        /**
         * This function converts a ByteArray to a hexadecimal string.
         *
         * @return The hexadecimal string representation of the ByteArray.
         */
        private fun ByteArray.toHexString() = joinToString("") { "%02x".format(it) }

        /**
         * This function hashes a raw string using a salt string with the PBKDF2 with Hmac SHA256 algorithm.
         *
         * @param raw The raw string to be hashed.
         * @param salt The salt string to be used in the hashing process.
         * @return The hashed string in hexadecimal format.
         */
        fun hash(raw: String, salt: String): String {
            val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
            val spec = PBEKeySpec(raw.toCharArray(), salt.toByteArray(), iterations, keyLength)
            val key = skf.generateSecret(spec)
            val res = key.encoded
            return res.toHexString()
        }
    }
}