package emiyaj.util

import org.apache.commons.codec.binary.Base64
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object AESEncryption {
    fun encrypt(raw: String) = encrypt(raw, KeyManager.defaultKey, KeyManager.defaultIv)

    fun decrypt(raw: String) = decrypt(raw, KeyManager.defaultKey, KeyManager.defaultIv)

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