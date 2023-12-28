package emiyaj.database

import java.sql.Connection
import java.sql.DriverManager

/**
 * 이 객체는 MySQL 데이터베이스와의 연결을 관리합니다.
 */
object MySQL {
    /**
     * 이 함수는 MySQL 데이터베이스에 연결을 시도하고, 연결을 반환합니다.
     * 데이터베이스 URL, 드라이버, 사용자 이름, 비밀번호를 사용하여 연결을 설정합니다.
     * 연결에 실패하면 null을 반환합니다.
     *
     * @return 데이터베이스 연결을 나타내는 Connection 객체, 또는 연결에 실패한 경우 null을 반환합니다.
     */
    fun getConnection(): Connection? {
        // MySQL 드라이버를 초기화하고 연결을 반환합니다.
        val url = "jdbc:mysql://localhost:3306/animal_crossing" // 데이터베이스 URL
        val driver = "com.mysql.cj.jdbc.Driver" // JDBC 드라이버
        val username = "root" // 데이터베이스 사용자 이름
        val password = "root" // 데이터베이스 비밀번호
        Class.forName(driver) // 드라이버 로드
        return try {
            DriverManager.getConnection(url, username, password) // 연결 시도
        } catch (e: Exception) {
            null // 연결 실패 시 null 반환
        }
    }
}