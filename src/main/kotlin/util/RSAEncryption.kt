package emiyaj.util

import java.nio.file.Files
import java.nio.file.Paths
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher


object RSAEncryption {
    fun encrypt(string: String): String {
        val rsa = Cipher.getInstance("RSA")
        val publicKey = getPublicKey(javaClass.getResource("/public.pkcs8").path)
        rsa.init(Cipher.ENCRYPT_MODE, publicKey)
        return Base64.getEncoder().encodeToString(rsa.doFinal(string.toByteArray()))
    }

    fun decrypt(base64: String): String {
        val rsa = Cipher.getInstance("RSA")
        val privateKey = getPrivateKey(javaClass.getResource("/private.pkcs8").path)
        rsa.init(Cipher.DECRYPT_MODE, privateKey)
        return rsa.doFinal(Base64.getDecoder().decode(base64)).toString(Charsets.UTF_8)
    }

    @Throws(Exception::class)
    fun getPrivateKey(filename: String?): PrivateKey? {
        val keyBytes = Files.readAllBytes(Paths.get(filename!!))
        val keySpecPKCS8 = PKCS8EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePrivate(keySpecPKCS8)
    }

    @Throws(Exception::class)
    fun getPublicKey(filename: String?): PublicKey? {
        val keyBytes = Files.readAllBytes(Paths.get(filename!!))
        val keySpecPKCS8 = X509EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePublic(keySpecPKCS8)
    }
}