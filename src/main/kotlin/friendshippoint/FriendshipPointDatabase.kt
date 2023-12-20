package emiyaj.friendshippoints

import emiyaj.database.MongoDB
import emiyaj.util.EnvVariable
import emiyaj.util.Validation
import org.litote.kmongo.*

/**
 * This is a singleton object that interacts with the MongoDB database for the Friendship Point module of the application.
 * It provides methods to add, retrieve, and remove friendship points for a user.
 */
object FriendshipPointDatabase {
    // The MongoDB collection where the friendship point data is stored.
    private val collection = MongoDB.getDatabase(EnvVariable.getMongoDatabase())?.getCollection<FriendshipPoint>("friendshippoint")

    /**
     * This method retrieves the friendship point document of a user from the database.
     *
     * @param id The ID of the user whose friendship point document is to be retrieved.
     * @return The friendship point document of the user, or null if the user's friendship point document is not found in the database.
     */
    private fun getPointDocument(id: String): FriendshipPoint? = collection?.findOne(FriendshipPoint::id eq id)

    /**
     * This method inserts a new friendship point document for a user into the database.
     *
     * @param id The ID of the user for whom the friendship point document is to be inserted.
     * @param points The friendship points to be inserted for the user.
     */
    private fun insertPointDocument(id: String, points: Map<String, Int>) = collection?.insertOne(FriendshipPoint(id, points))

    /**
     * This method updates the friendship point document of a user in the database.
     *
     * @param id The ID of the user whose friendship point document is to be updated.
     * @param points The new friendship points for the user.
     */
    private fun updatePointDocument(id: String, points: Map<String, Int>) {
        collection?.updateOne(FriendshipPoint::id eq id, FriendshipPoint::points setTo points)
    }

    /**
     * This method adds friendship points for a user in the database.
     *
     * @param id The ID of the user for whom the points are to be added.
     * @param code The code of the friendship point event.
     * @param point The number of points to be added.
     */
    fun addPoint(id: String, code: String, point: Int) {
        if (!Validation.villagerCode(code)) return
        val existPoints = getPointDocument(id)
        if (existPoints == null) {
            insertPointDocument(id, mapOf(Pair(code, point)))
        } else {
            val points = existPoints.points.toMutableMap()
            val existPoint = points[code] ?: 0
            points[code] = existPoint + point

            updatePointDocument(id, points)
        }
    }

    /**
     * This method retrieves the friendship points of a user from the database.
     *
     * @param id The ID of the user whose friendship points are to be retrieved.
     * @return The friendship points of the user, or an empty map if the user's friendship points are not found in the database.
     */
    fun getPoints(id: String): Map<String, Int> = getPointDocument(id)?.points ?: mapOf()

    /**
     * This method removes a villager's friendship points for a user in the database.
     *
     * @param id The ID of the user for whom the villager's points are to be removed.
     * @param code The code of the villager whose points are to be removed.
     */
    fun removeVillager(id: String, code: String) {
        if (!Validation.villagerCode(code)) return
        val existPoints = getPointDocument(id)
        if (existPoints != null) {
            val points = existPoints.points.toMutableMap()
            points.remove(code)
            updatePointDocument(id, points)
        }
    }
}