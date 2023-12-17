package emiyaj.util

import org.apache.commons.codec.binary.Base64
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * This object provides utility functions for AES encryption and decryption.
 * It uses the default key and initialization vector (IV) from the KeyManager object,
 * but also allows for custom keys and IVs.
 */
object AESEncryption {

    /**
     * This function encrypts a given string using the default AES key and IV.
     *
     * @param raw The string to be encrypted.
     * @return The encrypted string, Base64 encoded.
     */
    fun encrypt(raw: String) = encrypt(raw, KeyManager.defaultKey, KeyManager.defaultIv)

    /**
     * This function decrypts a given Base64 encoded string using the default AES key and IV.
     *
     * @param raw The string to be decrypted (Base64 encoded).
     * @return The decrypted string.
     */
    fun decrypt(raw: String) = decrypt(raw, KeyManager.defaultKey, KeyManager.defaultIv)

    /**
     * This function encrypts a given string using a custom AES key and IV.
     * It uses the Cipher class to perform the encryption.
     *
     * @param raw The string to be encrypted.
     * @param key The AES key to be used for encryption.
     * @param iv The AES IV to be used for encryption.
     * @return The encrypted string, Base64 encoded.
     */
    fun encrypt(raw: String, key: String, iv: String): String {
        val keySpec: SecretKey = SecretKeySpec(
            Base64.decodeBase64(key), "AES"
        )
        val ivSpec: AlgorithmParameterSpec = IvParameterSpec(
            Base64.decodeBase64(iv)
        )
        val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)
        return Base64.encodeBase64String(
            cipher.doFinal(
                raw.toByteArray(charset("UTF-8"))
            )
        )
    }

    /**
     * This function decrypts a given Base64 encoded string using a custom AES key and IV.
     * It uses the Cipher class to perform the decryption.
     *
     * @param base64 The string to be decrypted (Base64 encoded).
     * @param key The AES key to be used for decryption.
     * @param iv The AES IV to be used for decryption.
     * @return The decrypted string.
     */
    fun decrypt(base64: String, key: String, iv: String): String {
        val keySpec: SecretKey = SecretKeySpec(
            Base64.decodeBase64(key), "AES"
        )
        val ivSpec: AlgorithmParameterSpec = IvParameterSpec(
            Base64.decodeBase64(iv)
        )
        val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)
        return cipher.doFinal(Base64.decodeBase64(base64)).toString(Charsets.UTF_8)
    }
}