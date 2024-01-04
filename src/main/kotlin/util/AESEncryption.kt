package emiyaj.util

import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * `AESEncryption` 클래스는 AES 암호화와 복호화를 수행하는 기능을 제공합니다.
 * 이 클래스는 생성 시에 키를 받아 AES 암호화와 복호화에 사용합니다. 키가 제공되지 않으면 무작위 키를 생성합니다.
 *
 * @property key AES 암호화와 복호화에 사용할 키입니다. 기본값은 무작위로 생성된 키입니다.
 * @property iv AES 암호화와 복호화에 사용할 초기 벡터입니다. 키를 기반으로 생성됩니다.
 * @property secretKey AES 암호화와 복호화에 사용할 비밀 키입니다. 키를 기반으로 생성됩니다.
 */
class AESEncryption(private val key: ByteArray = generateRandomKey()) {
    val iv = IvParameterSpec(key, 0, 16)
    val secretKey = SecretKeySpec(key, "AES")

    /**
     * 주어진 문자열을 AES 암호화합니다.
     *
     * @param raw 암호화할 원시 문자열입니다.
     * @return 암호화된 문자열을 Base64 인코딩하여 반환합니다.
     */
    fun encrypt(raw: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv)

        val encrypted = cipher.doFinal(raw.toByteArray())
        return Base64.getEncoder().encodeToString(encrypted)
    }

    /**
     * 주어진 문자열을 AES 복호화합니다.
     *
     * @param encrypted 복호화할 암호화된 문자열입니다. 이 문자열은 Base64로 인코딩되어 있어야 합니다.
     * @return 복호화된 문자열을 반환합니다.
     */
    fun decrypt(encrypted: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv)

        val decrypted = cipher.doFinal(Base64.getDecoder().decode(encrypted))
        return String(decrypted)
    }

    /**
     * 무작위 키를 생성하는 동반 객체입니다.
     */
    companion object {
        /**
         * 무작위 키를 생성합니다.
         *
         * @return 생성된 무작위 키를 바이트 배열로 반환합니다.
         */
        fun generateRandomKey(): ByteArray {
            val key = ByteArray(32)
            val random = SecureRandom()
            random.nextBytes(key)
            return key
        }
    }
}