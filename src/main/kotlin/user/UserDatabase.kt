package emiyaj.user

import emiyaj.database.MongoDB
import emiyaj.user.model.User
import emiyaj.util.EnvVariable
import emiyaj.util.PBKDF2
import org.litote.kmongo.*
import org.litote.kmongo.util.idValue

object UserDatabase {
    private val collection = MongoDB.getDatabase(EnvVariable.getMongoDatabase())?.getCollection<User>("users")

    fun getAllUsers(): List<User> = collection?.find()?.toList() ?: listOf()

    fun getUser(username: String): User? = collection?.findOne(User::username eq username)

    fun getUserById(id: String): User? = collection?.findOne(User::idValue eq id)

    fun getUserByEmail(email: String): User? = collection?.findOne(User::email eq email)

    fun getUserByUsernameAndPasswordHash(username: String, passwordHash: String): User? {
        return collection?.findOne(and(User::username eq username, User::hash eq passwordHash))
    }

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

    fun authenticate(username: String, password: String, useHash: Boolean = true): User? {
        return if (useHash) {
            getUserByUsernameAndPasswordHash(username, password)
        } else {
            getUserByUsernameAndPassword(username, password)
        }
    }

    fun migrateMyVillagers(username: String): Boolean {
        val user = getUser(username) ?: return false
        val myVillagers = user.myVillagers
        val jsonObj = mutableMapOf<String, List<String>>()
        jsonObj["Default"] = myVillagers.toList()
        collection?.updateOne((User::username eq user.username), set(User::villagers setTo jsonObj, User::villagersGroup setTo "Default"))
        return true
    }
}