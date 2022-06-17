package emiyaj.friendshippoints

import emiyaj.database.MongoDB
import emiyaj.util.EnvVariable
import emiyaj.util.Validation
import org.litote.kmongo.*

object FriendshipPointDatabase {
    private val collection = MongoDB.getDatabase(EnvVariable.getMongoDatabase())?.getCollection<FriendshipPoint>("friendshippoint")

    private fun getPointDocument(id: String): FriendshipPoint? = collection?.findOne(FriendshipPoint::id eq id)

    private fun insertPointDocument(id: String, points: Map<String, Int>) = collection?.insertOne(FriendshipPoint(id, points))

    private fun updatePointDocument(id: String, points: Map<String, Int>) {
        collection?.updateOne(FriendshipPoint::id eq id, FriendshipPoint::points setTo points)
    }

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

    fun getPoints(id: String): Map<String, Int> = getPointDocument(id)?.points ?: mapOf()

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