package emiyaj.util

import emiyaj.database.MySQL
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * `ImageStorage` 객체는 이미지 업로드 및 다운로드와 관련된 기능을 제공합니다.
 * 이 객체는 이미지를 업로드하고, 업로드된 이미지를 가져오는 기능을 제공합니다.
 * 또한, Base64 문자열을 InputStream으로 변환하고, 디렉토리를 확인하고 생성하는 기능도 제공합니다.
 * 마지막으로, 업로더 ID, 키, 경로를 사용하여 이미지 정보를 데이터베이스에 저장하고, 랜덤한 키를 생성하는 기능도 제공합니다.
 */
object ImageStorage {
    /**
     * 주어진 업로더 ID와 Base64 문자열을 사용하여 이미지를 업로드하는 함수입니다.
     * 이 함수는 랜덤한 키를 생성하고, 이 키를 사용하여 이미지를 저장할 경로를 결정합니다.
     * 그런 다음, 이 키와 경로를 데이터베이스에 저장하고, 경로에 디렉토리가 있는지 확인합니다.
     * 디렉토리가 없다면 새로 생성하고, 디렉토리가 존재하면 Base64 문자열을 바이트 스트림으로 변환하여 해당 경로에 파일로 복사합니다.
     * 마지막으로, 생성된 키를 반환합니다.
     *
     * @param uploader 이미지를 업로드한 사용자의 ID입니다.
     * @param base64 업로드할 이미지를 나타내는 Base64 문자열입니다.
     * @return 업로드된 이미지에 할당된 키를 문자열로 반환합니다.
     */
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

    /**
     * 주어진 키에 해당하는 이미지를 가져오는 함수입니다.
     * 이 함수는 MySQL 데이터베이스에 연결하여 "image" 테이블에서 키에 해당하는 레코드를 조회합니다.
     * 조회된 레코드의 "path" 필드를 사용하여 이미지 파일을 읽어 InputStream으로 반환합니다.
     *
     * @param key 가져올 이미지의 키를 나타내는 문자열입니다.
     * @return 키에 해당하는 이미지 파일을 읽어 생성한 InputStream 객체를 반환합니다. 키에 해당하는 레코드가 없으면 null을 반환합니다.
     */
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
    /**
     * 주어진 Base64 문자열을 InputStream으로 변환하는 함수입니다.
     * 이 함수는 kotlin.io.encoding 패키지의 Base64 클래스를 사용하여 Base64 문자열을 디코딩합니다.
     * 디코딩된 바이트 배열을 ByteArrayInputStream으로 변환하여 반환합니다.
     *
     * @param base64String InputStream으로 변환할 Base64 문자열입니다.
     * @return Base64 문자열을 디코딩하여 생성한 ByteArrayInputStream 객체를 반환합니다.
     */
    private fun base64ToInputStream(base64String: String): InputStream {
        //val base64String = base64String.replace("data:image/png;base64,", "")
        val decodedBytes = Base64.decode(base64String)
        return ByteArrayInputStream(decodedBytes)
    }

    /**
     * 주어진 경로에 디렉토리가 있는지 확인하고, 없다면 생성하는 함수입니다.
     * 이 함수는 java.nio.file 패키지의 Files와 Paths 클래스를 사용하여 파일 시스템을 조작합니다.
     *
     * @param path 디렉토리의 경로를 나타내는 문자열입니다.
     * @return 디렉토리가 존재하고 쓰기 가능하면 true를 반환하고, 디렉토리가 없어서 새로 생성하면 true를 반환합니다.
     * 디렉토리 생성에 실패하면 false를 반환합니다.
     */
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

    /**
     * 주어진 업로더 ID, 키, 경로를 사용하여 이미지 정보를 데이터베이스에 저장하는 함수입니다.
     * 이 함수는 MySQL 데이터베이스에 연결하여 "image" 테이블에 새로운 레코드를 추가합니다.
     *
     * @param uploader 이미지를 업로드한 사용자의 ID입니다.
     * @param key 이미지에 할당된 고유 키입니다.
     * @param path 이미지 파일이 저장된 경로입니다.
     */
    private fun saveKeyImagePathToDatabase(uploader: Int, key: String, path: String) {
        try {
            // 키값을 데이터베이스에 저장함
            val connection = MySQL.getConnection();
            val statement =
                connection?.prepareStatement("INSERT INTO image (uploader, `key`, path, fileType) VALUES (?, ?, ?, ?)")
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

    /**
     * 주어진 크기의 랜덤한 키를 생성하는 함수입니다.
     * 생성된 키는 소문자 알파벳(a-z), 대문자 알파벳(A-Z), 숫자(0-9)를 랜덤하게 조합하여 만들어집니다.
     *
     * @param size 생성할 키의 크기입니다. 기본값은 16입니다.
     * @return 생성된 랜덤 키를 문자열로 반환합니다.
     */
    private fun generateKey(size: Int = 16): String {
        // 랜덤한 키값을 생성함 generate random string by size
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return List(size) { chars.random() }.joinToString("")
    }
}