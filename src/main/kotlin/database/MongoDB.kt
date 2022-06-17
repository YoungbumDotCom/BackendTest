package emiyaj.database

import com.mongodb.ConnectionString
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import emiyaj.util.EnvVariable
import org.litote.kmongo.*

object MongoDB {
    private val connection = getConnection()

    private fun getConnection(): MongoClient? {
        val connectionString = EnvVariable.getVariable("mongo_uri") ?: return null
        return KMongo.createClient(ConnectionString(connectionString))
    }

    fun closeDatabase() {
        connection?.close()
    }

    fun getDatabase(database: String): MongoDatabase? = connection?.getDatabase(database)
}