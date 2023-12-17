package emiyaj.database

import com.mongodb.ConnectionString
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import emiyaj.util.EnvVariable
import org.litote.kmongo.*

/**
 * This object provides utility functions for connecting to a MongoDB database.
 * It uses the KMongo library for MongoDB operations.
 */
object MongoDB {
    // The MongoClient instance used for database connections
    private val connection = getConnection()

    /**
     * This function establishes a connection to the MongoDB database.
     * It retrieves the connection string from an environment variable and uses it to create a MongoClient instance.
     *
     * @return The MongoClient instance, or null if the connection string is not set.
     */
    private fun getConnection(): MongoClient? {
        val connectionString = EnvVariable.getVariable("mongo_uri") ?: return null
        return KMongo.createClient(ConnectionString(connectionString))
    }

    /**
     * This function closes the MongoDB database connection.
     */
    fun closeDatabase() {
        connection?.close()
    }

    /**
     * This function retrieves a MongoDatabase instance for a given database name.
     *
     * @param database The name of the database.
     * @return The MongoDatabase instance, or null if the MongoClient instance is not set.
     */
    fun getDatabase(database: String): MongoDatabase? = connection?.getDatabase(database)
}