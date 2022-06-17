package emiyaj.campsite

import emiyaj.campsite.model.Campsite
import emiyaj.campsite.model.CampsiteContent
import emiyaj.database.MongoDB
import emiyaj.user.model.User
import emiyaj.util.EnvVariable
import org.litote.kmongo.*

object CampsiteDatabase {
    private val collection = MongoDB.getDatabase(EnvVariable.getMongoDatabase())?.getCollection<Campsite>("campsite")

    fun overwriteContent(user: User, content: List<CampsiteContent>) {
        collection?.updateOne(Campsite::uid eq user._id, set(Campsite::content setTo content), upsert())
    }

    fun getContent(user: User): List<CampsiteContent>? = collection?.findOne(Campsite::uid eq user._id)?.content
}