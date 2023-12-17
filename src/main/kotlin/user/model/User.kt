package emiyaj.user.model

/**
 * This data class represents a User in the application.
 * It includes various properties related to the user, such as ID, villagers, email, username, password hash, and others.
 *
 * @property _id The ID of the user.
 * @property myVillagers An array of villagers that belong to the user.
 * @property villagers A map of villagers groups that belong to the user. The key is the group name and the value is a list of villagers in that group.
 * @property villagersGroup The default group of villagers for the user.
 * @property email The email of the user.
 * @property username The username of the user.
 * @property verified A boolean indicating whether the user's email is verified.
 * @property verifyHash The hash used for email verification.
 * @property salt The salt used for password hashing.
 * @property hash The hashed password of the user.
 * @property __v The version key used by MongoDB for internal record keeping.
 * @property resetPasswordHash The hash used for password reset functionality. This is optional and defaults to null.
 * @property resetPasswordTime The timestamp for when the password reset was requested. This is optional and defaults to null.
 * @property language The preferred language of the user. This is optional and defaults to null.
 * @property admin A boolean indicating whether the user is an admin. This is optional and defaults to null.
 */
data class User (
    val _id: String,
    val myVillagers: Array<String>,
    val villagers: Map<String, List<String>>,
    val villagersGroup: String,
    val email: String,
    val username: String,
    val verified: Boolean,
    val verifyHash: String,
    val salt: String,
    val hash: String,
    val __v: Int,
    val resetPasswordHash: String?,
    val resetPasswordTime: Long?,
    val language: String?,
    val admin: Boolean?
) {
    /**
     * This function checks if this User is equal to another object.
     * It returns true if the other object is a User and has the same villagers as this User.
     *
     * @param other The object to compare with this User.
     * @return True if the other object is a User and has the same villagers, false otherwise.
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (!myVillagers.contentEquals(other.myVillagers)) return false

        return true
    }

    /**
     * This function generates a hash code for this User.
     * It uses the content hash code of the villagers array for the hash code.
     *
     * @return The hash code of this User.
     */
    override fun hashCode(): Int {
        return myVillagers.contentHashCode()
    }
}