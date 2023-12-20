package emiyaj.campsite

import emiyaj.campsite.model.Campsite
import emiyaj.campsite.model.CampsiteContent
import emiyaj.database.MongoDB
import emiyaj.user.model.User
import emiyaj.util.EnvVariable
import org.litote.kmongo.*

/**
 * This is a singleton object that interacts with the MongoDB database for the Campsite module of the application.
 * It provides methods to overwrite and retrieve the campsite content of a user.
 */
object CampsiteDatabase {
    // The MongoDB collection where the campsite data is stored.
    private val collection = MongoDB.getDatabase(EnvVariable.getMongoDatabase())?.getCollection<Campsite>("campsite")

    /**
     * This method overwrites the campsite content of a user in the database.
     *
     * @param user The user whose campsite content is to be overwritten.
     * @param content The new campsite content for the user.
     */
    fun overwriteContent(user: User, content: List<CampsiteContent>) {
        collection?.updateOne(Campsite::uid eq user._id, set(Campsite::content setTo content), upsert())
    }

    /**
     * This method retrieves the campsite content of a user from the database.
     *
     * @param user The user whose campsite content is to be retrieved.
     * @return The campsite content of the user, or null if the user's campsite content is not found in the database.
     */
    fun getContent(user: User): List<CampsiteContent>? = collection?.findOne(Campsite::uid eq user._id)?.content
}