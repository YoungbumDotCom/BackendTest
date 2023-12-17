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

/**
 * This object provides utility functions for RSA encryption and decryption.
 */
object RSAEncryption {

    /**
     * This function encrypts a given string using RSA encryption algorithm.
     * It retrieves the public key from the "/public.pkcs8" path.
     *
     * @param string The string to be encrypted.
     * @return The encrypted string, Base64 encoded.
     */
    fun encrypt(string: String): String {
        val rsa = Cipher.getInstance("RSA")
        val publicKey = getPublicKey(javaClass.getResource("/public.pkcs8").path)
        rsa.init(Cipher.ENCRYPT_MODE, publicKey)
        return Base64.getEncoder().encodeToString(rsa.doFinal(string.toByteArray()))
    }

    /**
     * This function decrypts a given Base64 encoded string using RSA encryption algorithm.
     * It retrieves the private key from the "/private.pkcs8" path.
     *
     * @param base64 The string to be decrypted (Base64 encoded).
     * @return The decrypted string.
     */
    fun decrypt(base64: String): String {
        val rsa = Cipher.getInstance("RSA")
        val privateKey = getPrivateKey(javaClass.getResource("/private.pkcs8").path)
        rsa.init(Cipher.DECRYPT_MODE, privateKey)
        return rsa.doFinal(Base64.getDecoder().decode(base64)).toString(Charsets.UTF_8)
    }

    /**
     * This function retrieves a private key from a given path.
     *
     * @param filename The path of the private key file.
     * @return The private key.
     * @throws Exception Any exceptions that may occur while retrieving the key.
     */
    @Throws(Exception::class)
    fun getPrivateKey(filename: String?): PrivateKey? {
        val keyBytes = Files.readAllBytes(Paths.get(filename!!))
        val keySpecPKCS8 = PKCS8EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePrivate(keySpecPKCS8)
    }

    /**
     * This function retrieves a public key from a given path.
     *
     * @param filename The path of the public key file.
     * @return The public key.
     * @throws Exception Any exceptions that may occur while retrieving the key.
     */
    @Throws(Exception::class)
    fun getPublicKey(filename: String?): PublicKey? {
        val keyBytes = Files.readAllBytes(Paths.get(filename!!))
        val keySpecPKCS8 = X509EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePublic(keySpecPKCS8)
    }
}