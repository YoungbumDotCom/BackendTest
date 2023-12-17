package emiyaj.user

import emiyaj.database.MongoDB
import emiyaj.user.model.User
import emiyaj.util.EnvVariable
import emiyaj.util.PBKDF2
import org.litote.kmongo.*
import org.litote.kmongo.util.idValue

/**
 * This object provides utility functions for interacting with the user collection in the MongoDB database.
 * It uses the KMongo library for MongoDB operations.
 */
object UserDatabase {
    // The user collection in the MongoDB database
    private val collection = MongoDB.getDatabase(EnvVariable.getMongoDatabase())?.getCollection<User>("users")

    /**
     * This function retrieves all users from the user collection.
     *
     * @return A list of all users, or an empty list if the collection is null.
     */
    fun getAllUsers(): List<User> = collection?.find()?.toList() ?: listOf()

    /**
     * This function retrieves a user by username from the user collection.
     *
     * @param username The username of the user.
     * @return The user with the given username, or null if the user does not exist.
     */
    fun getUser(username: String): User? = collection?.findOne(User::username eq username)

    /**
     * This function retrieves a user by ID from the user collection.
     *
     * @param id The ID of the user.
     * @return The user with the given ID, or null if the user does not exist.
     */
    fun getUserById(id: String): User? = collection?.findOne(User::idValue eq id)

    /**
     * This function retrieves a user by email from the user collection.
     *
     * @param email The email of the user.
     * @return The user with the given email, or null if the user does not exist.
     */
    fun getUserByEmail(email: String): User? = collection?.findOne(User::email eq email)

    /**
     * This function retrieves a user by username and password hash from the user collection.
     *
     * @param username The username of the user.
     * @param passwordHash The password hash of the user.
     * @return The user with the given username and password hash, or null if the user does not exist.
     */
    fun getUserByUsernameAndPasswordHash(username: String, passwordHash: String): User? {
        return collection?.findOne(and(User::username eq username, User::hash eq passwordHash))
    }

    /**
     * This function retrieves a user by username and password from the user collection.
     * It hashes the given password with the user's salt and compares it with the user's stored password hash.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The user with the given username and password, or null if the user does not exist or the password is incorrect.
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
     * This function authenticates a user by username and password or password hash.
     *
     * @param username The username of the user.
     * @param password The password or password hash of the user.
     * @param useHash A boolean indicating whether to use the password as a hash. Default is true.
     * @return The authenticated user, or null if the user does not exist or the password is incorrect.
     */
    fun authenticate(username: String, password: String, useHash: Boolean = true): User? {
        return if (useHash) {
            getUserByUsernameAndPasswordHash(username, password)
        } else {
            getUserByUsernameAndPassword(username, password)
        }
    }

    /**
     * This function migrates a user's villagers from a list to a map with a default group.
     * It updates the user's villagers and villagersGroup fields in the user collection.
     *
     * @param username The username of the user.
     * @return True if the migration was successful, false otherwise.
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