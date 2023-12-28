package emiyaj.util

import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * 이 클래스는 PBKDF2와 Hmac SHA256 알고리즘을 사용하여 문자열을 해싱하는 유틸리티를 제공합니다.
 */
class PBKDF2 {
    companion object {
        // PBKDF2 알고리즘의 반복 횟수
        private const val iterations = 25000
        // 생성된 키의 길이
        private const val keyLength = 512 * 8

        /**
         * 이 함수는 ByteArray를 16진수 문자열로 변환합니다.
         *
         * @return ByteArray의 16진수 문자열 표현.
         */
        private fun ByteArray.toHexString() = joinToString("") { "%02x".format(it) }

        /**
         * 이 함수는 PBKDF2와 Hmac SHA256 알고리즘을 사용하여 원시 문자열을 해싱하고,
         * 해싱 과정에서 솔트 문자열을 사용합니다.
         *
         * @param raw 해싱할 원시 문자열입니다.
         * @param salt 해싱 과정에서 사용할 솔트 문자열입니다.
         * @return 16진수 형식의 해싱된 문자열을 반환합니다.
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