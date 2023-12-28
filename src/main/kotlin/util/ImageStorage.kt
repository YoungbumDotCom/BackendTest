package emiyaj.util

import emiyaj.database.MySQL
import java.io.InputStream

object ImageStorage {
    fun uploadImage(uploader: Int, s: InputStream): String {
        // 이미지를 업로드 특정 폴더에 업로드하고 다운로드할 수 있는 키값을 반환함
        val newKey = generateKey()
        val path = "images/$newKey"
        saveKeyImagePathToDatabase(uploader, newKey, path)
        //TODO: 이미지를 업로드하는 코드를 작성해야 함
        return newKey
    }

    fun saveKeyImagePathToDatabase(uploader: Int, key: String, path: String) {
        try {
            // 키값을 데이터베이스에 저장함
            val connection = MySQL.getConnection();
            val statement = connection?.prepareStatement("INSERT INTO image (uploader, key, path) VALUES (?, ?, ?)")
            statement?.setInt(1, uploader)
            statement?.setString(2, key)
            statement?.setString(3, path)
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