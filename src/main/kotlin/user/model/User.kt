package emiyaj.user.model

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
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (!myVillagers.contentEquals(other.myVillagers)) return false

        return true
    }

    override fun hashCode(): Int {
        return myVillagers.contentHashCode()
    }
}