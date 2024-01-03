package emiyaj.util

import emiyaj.database.MySQL
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

object ImageStorage {
    fun uploadImage(uploader: Int, base64: String): String {
        // 이미지를 업로드 특정 폴더에 업로드하고 다운로드할 수 있는 키값을 반환함
        val newKey = generateKey()
        val path = "images/$newKey"
        saveKeyImagePathToDatabase(uploader, newKey, path)
        if (checkAndCreatePath(path)) {
            Files.copy(base64.byteInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING)
        }
        return newKey
    }

    fun getImage(key: String): InputStream? {
        val connection = MySQL.getConnection()
        val statement = connection?.prepareStatement("SELECT * FROM image WHERE `key` = ?")
        statement?.setString(1, key)
        val resultSet = statement?.executeQuery()
        return if (resultSet?.next() == true) {
            Files.newInputStream(Paths.get(resultSet.getString("path")))
        } else null
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun base64ToInputStream(base64String: String): InputStream {
        //val base64String = base64String.replace("data:image/png;base64,", "")
        val decodedBytes = Base64.decode(base64String)
        return ByteArrayInputStream(decodedBytes)
    }

    private fun checkAndCreatePath(path: String): Boolean {
        val filePath = Paths.get(path)
        return if (Files.exists(filePath)) {
            Files.isWritable(filePath)
        } else {
            try {
                Files.createDirectories(filePath)
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

    private fun saveKeyImagePathToDatabase(uploader: Int, key: String, path: String) {
        try {
            // 키값을 데이터베이스에 저장함
            val connection = MySQL.getConnection();
            val statement = connection?.prepareStatement("INSERT INTO image (uploader, `key`, path, fileType) VALUES (?, ?, ?, ?)")
            statement?.setInt(1, uploader)
            statement?.setString(2, key)
            statement?.setString(3, path)
            statement?.setString(4, "")
            //statement?.setString(4, fileType)
            statement?.executeUpdate()
            statement?.close()
            connection?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun generateKey(size: Int = 16): String {
        // 랜덤한 키값을 생성함 generate random string by size
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return List(size) { chars.random() }.joinToString("")
    }
}