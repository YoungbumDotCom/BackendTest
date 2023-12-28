package emiyaj.user

import emiyaj.database.MongoDB
import emiyaj.user.model.User
import emiyaj.util.EnvVariable
import emiyaj.util.PBKDF2
import org.litote.kmongo.*
import org.litote.kmongo.util.idValue

/**
 * 이 객체는 MongoDB 데이터베이스의 사용자 컬렉션과 상호 작용하는 유틸리티 함수를 제공합니다.
 * MongoDB 작업에는 KMongo 라이브러리를 사용합니다.
 */
object UserDatabase {
    // MongoDB 데이터베이스의 사용자 컬렉션
    private val collection = MongoDB.getDatabase(EnvVariable.getMongoDatabase())?.getCollection<User>("users")

    /**
     * 이 함수는 사용자 컬렉션에서 모든 사용자를 검색합니다.
     *
     * @return 모든 사용자의 목록, 또는 컬렉션이 null인 경우 빈 목록을 반환합니다.
     */
    fun getAllUsers(): List<User> = collection?.find()?.toList() ?: listOf()

    /**
     * 이 함수는 사용자 컬렉션에서 사용자 이름으로 사용자를 검색합니다.
     *
     * @param username 사용자의 사용자 이름입니다.
     * @return 주어진 사용자 이름을 가진 사용자, 또는 사용자가 존재하지 않는 경우 null을 반환합니다.
     */
    fun getUser(username: String): User? = collection?.findOne(User::username eq username)

    /**
     * 이 함수는 사용자 컬렉션에서 ID로 사용자를 검색합니다.
     *
     * @param id 사용자의 ID입니다.
     * @return 주어진 ID를 가진 사용자, 또는 사용자가 존재하지 않는 경우 null을 반환합니다.
     */
    fun getUserById(id: String): User? = collection?.findOne(User::idValue eq id)

    /**
     * 이 함수는 사용자 컬렉션에서 이메일로 사용자를 검색합니다.
     *
     * @param email 사용자의 이메일입니다.
     * @return 주어진 이메일을 가진 사용자, 또는 사용자가 존재하지 않는 경우 null을 반환합니다.
     */
    fun getUserByEmail(email: String): User? = collection?.findOne(User::email eq email)

    /**
     * 이 함수는 사용자 컬렉션에서 사용자 이름과 비밀번호 해시로 사용자를 검색합니다.
     *
     * @param username 사용자의 사용자 이름입니다.
     * @param passwordHash 사용자의 비밀번호 해시입니다.
     * @return 주어진 사용자 이름과 비밀번호 해시를 가진 사용자, 또는 사용자가 존재하지 않는 경우 null을 반환합니다.
     */
    fun getUserByUsernameAndPasswordHash(username: String, passwordHash: String): User? {
        return collection?.findOne(and(User::username eq username, User::hash eq passwordHash))
    }

    /**
     * 이 함수는 사용자 컬렉션에서 사용자 이름과 비밀번호로 사용자를 검색합니다.
     * 주어진 비밀번호를 사용자의 솔트로 해시하고 사용자의 저장된 비밀번호 해시와 비교합니다.
     *
     * @param username 사용자의 사용자 이름입니다.
     * @param password 사용자의 비밀번호입니다.
     * @return 주어진 사용자 이름과 비밀번호를 가진 사용자, 또는 사용자가 존재하지 않거나 비밀번호가 틀린 경우 null을 반환합니다.
     */
    fun getUserByUsernameAndPassword(username: String, password: String): User? {
        val userByUsername = collection?.findOne(User::username eq username) ?: return null
        val salt = userByUsername.salt
        val hash = userByUsername.hash
        val generatedHash = PBKDF2.hash(password, salt)
        return if (generatedHash == hash) {
            userByUsername
        } else {
            null
        }
    }

    /**
     * 이 함수는 사용자 이름과 비밀번호 또는 비밀번호 해시로 사용자를 인증합니다.
     *
     * @param username 사용자의 사용자 이름입니다.
     * @param password 사용자의 비밀번호 또는 비밀번호 해시입니다.
     * @param useHash 비밀번호를 해시로 사용할지 여부를 나타내는 부울입니다. 기본값은 true입니다.
     * @return 인증된 사용자, 또는 사용자가 존재하지 않거나 비밀번호가 틀린 경우 null을 반환합니다.
     */
    fun authenticate(username: String, password: String, useHash: Boolean = true): User? {
        return if (useHash) {
            getUserByUsernameAndPasswordHash(username, password)
        } else {
            getUserByUsernameAndPassword(username, password)
        }
    }

    /**
     * 이 함수는 사용자의 주민들을 기본 그룹을 가진 맵으로 마이그레이션합니다.
     * 사용자의 주민들과 주민들의 그룹 필드를 사용자 컬렉션에서 업데이트합니다.
     *
     * @param username 사용자의 사용자 이름입니다.
     * @return 마이그레이션이 성공하면 true, 그렇지 않으면 false를 반환합니다.
     */
    fun migrateMyVillagers(username: String): Boolean {
        val user = getUser(username) ?: return false
        val myVillagers = user.myVillagers
        val jsonObj = mutableMapOf<String, List<String>>()
        jsonObj["Default"] = myVillagers.toList()
        collection?.updateOne((User::username eq user.username), set(User::villagers setTo jsonObj, User::villagersGroup setTo "Default"))
        return true
    }
}