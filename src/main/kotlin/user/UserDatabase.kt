package emiyaj.user

import emiyaj.database.MySQL
import emiyaj.user.model.User
import java.sql.ResultSet
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * 사용자 데이터베이스를 관리하는 객체입니다.
 * 이 객체는 MySQL 데이터베이스에 연결하여 사용자 정보를 생성, 조회, 인증하는 기능을 제공합니다.
 */
object UserDatabase {
    /**
     * ResultSet에서 User 객체를 파싱하는 함수입니다.
     * 이 함수는 ResultSet의 각 컬럼을 읽어와 User 객체를 생성합니다.
     *
     * @param rs 사용자 정보를 담고 있는 ResultSet 객체입니다.
     * @return User 객체를 반환합니다.
     */
    private fun parseUserFromResultSet(rs: ResultSet): User {
        return User(
            id = rs.getInt("id"),
            password = rs.getString("password"),
            email = rs.getString("email"),
            modified = rs.getString("modified"),
            phone = rs.getString("phone"),
            text = rs.getString("text"),
            verify = rs.getString("verify"),
            birth = rs.getString("birth"),
            name = rs.getString("name"),
            nickname = rs.getString("nickname"),
            created = rs.getTimestamp("created_date").toLocalDateTime()
        )
    }

    /**
     * 모든 사용자를 가져오는 함수입니다.
     * 이 함수는 MySQL 데이터베이스에 연결하여 모든 사용자 정보를 가져옵니다.
     *
     * @return User 객체의 리스트를 반환합니다.
     */
    fun getAllUsers(): List<User> {
        val connection = MySQL.getConnection()
        val statement = connection?.createStatement()
        val resultSet = statement?.executeQuery("SELECT * FROM member")
        val users = mutableListOf<User>()
        if (resultSet?.next() == true) {
            users.add(parseUserFromResultSet(resultSet))
        }
        return users
    }

    /**
     * 이메일을 통해 사용자를 가져오는 함수입니다.
     * 이 함수는 MySQL 데이터베이스에 연결하여 주어진 이메일을 가진 사용자 정보를 가져옵니다.
     *
     * @param email 사용자의 이메일입니다.
     * @return User 객체를 반환합니다. 해당 이메일을 가진 사용자가 없을 경우 null을 반환합니다.
     */
    fun getUserByEmail(email: String): User? {
        val connection = MySQL.getConnection()
        val statement = connection?.prepareStatement("SELECT * FROM member WHERE email = ?")
        statement?.setString(1, email)
        val resultSet = statement?.executeQuery()
        return if (resultSet?.next() == true) {
            parseUserFromResultSet(resultSet)
        } else null
    }

    /**
     * ID를 통해 사용자를 가져오는 함수입니다.
     * 이 함수는 MySQL 데이터베이스에 연결하여 주어진 ID를 가진 사용자 정보를 가져옵니다.
     *
     * @param id 사용자의 ID입니다.
     * @return User 객체를 반환합니다. 해당 ID를 가진 사용자가 없을 경우 null을 반환합니다.
     */
    fun getUserById(id: String): User? {
        val connection = MySQL.getConnection()
        val statement = connection?.prepareStatement("SELECT * FROM member WHERE id = ?")
        statement?.setString(1, id)
        val resultSet = statement?.executeQuery()
        return if (resultSet?.next() == true) {
            parseUserFromResultSet(resultSet)
        } else null
    }

    /**
     * 이메일과 비밀번호 해시를 통해 사용자를 가져오는 함수입니다.
     * 이 함수는 MySQL 데이터베이스에 연결하여 주어진 이메일과 비밀번호 해시를 가진 사용자 정보를 가져옵니다.
     *
     * @param email 사용자의 이메일입니다.
     * @param passwordHash 사용자의 비밀번호 해시입니다.
     * @return User 객체를 반환합니다. 해당 이메일과 비밀번호 해시를 가진 사용자가 없을 경우 null을 반환합니다.
     */
    fun getUserByEmailAndPasswordHash(email: String, passwordHash: String): User? {
        val connection = MySQL.getConnection()
        val statement = connection?.prepareStatement("SELECT * FROM member WHERE email = ? AND password = ?")
        statement?.setString(1, email)
        statement?.setString(2, passwordHash)
        val resultSet = statement?.executeQuery()
        return if (resultSet?.next() == true) {
             parseUserFromResultSet(resultSet)
        } else null
    }

    /**
     * 사용자를 인증하는 함수입니다.
     * 이 함수는 주어진 이메일과 비밀번호를 이용하여 사용자를 인증합니다.
     *
     * @param email 사용자의 이메일입니다.
     * @param password 사용자의 비밀번호입니다.
     * @param useHash 비밀번호 해시를 사용할지 여부입니다. 기본값은 true입니다.
     * @return User 객체를 반환합니다. 인증에 실패한 경우 null을 반환합니다.
     */
    fun authenticate(email: String, password: String, useHash: Boolean = true): User? {
        return getUserByEmailAndPasswordHash(email, password)
    }

    /**
     * 사용자를 생성하는 함수입니다.
     * 이 함수는 MySQL 데이터베이스에 연결하고, 사용자 정보를 이용해 새로운 사용자를 생성합니다.
     *
     * @param user 생성할 사용자 정보를 담고 있는 User 객체입니다.
     */
    private fun createUser(user: User): User? {
        try {
            // MySQL 데이터베이스에 연결합니다.
            val connection = MySQL.getConnection()

            // 새로운 사용자를 생성하는 SQL 문을 준비합니다.
            val statement =
                connection?.prepareStatement("INSERT INTO member (password, email, modified, phone, text, verify, birth, name, nickname, created_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")

            // SQL 문의 각 파라미터에 사용자 정보를 설정합니다.
            statement?.setString(1, user.password)
            statement?.setString(2, user.email)
            statement?.setString(3, user.modified)
            statement?.setString(4, user.phone)
            statement?.setString(5, user.text)
            statement?.setString(6, user.verify)
            statement?.setString(7, user.birth)
            statement?.setString(8, user.name)
            statement?.setString(9, user.nickname)
            statement?.setTimestamp(10, Timestamp(user.created.toEpochSecond(ZoneOffset.of("+09:00"))))


            // SQL 문을 실행하여 사용자를 생성합니다.
            statement?.executeUpdate()
            return user
        } catch (e: Exception) {
            // 사용자 생성에 실패한 경우, null을 반환합니다.
            return null
        }
    }

    /**
     * 이메일, 비밀번호, 이름, 별명을 인자로 받아 새로운 사용자를 생성하는 함수입니다.
     * 이 함수는 User 객체를 생성하고, 이를 이용해 createUser 함수를 호출하여 사용자를 생성합니다.
     *
     * @param email 생성할 사용자의 이메일입니다.
     * @param password 생성할 사용자의 비밀번호입니다.
     * @param name 생성할 사용자의 이름입니다.
     * @param nickname 생성할 사용자의 별명입니다.
     */
    fun createUser(email: String, password: String, name: String, nickname: String): User? {
        return createUser(
            User(
                id = 0,
                password = password,
                email = email,
                modified = "",
                phone = "111-111-1111",
                text = "111-111-1111",
                verify = "",
                birth = "",
                name = "",
                nickname = "",
                created = LocalDateTime.now()
            )
        )
    }
}