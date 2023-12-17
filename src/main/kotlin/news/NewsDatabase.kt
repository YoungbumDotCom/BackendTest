package emiyaj.news

import emiyaj.database.MongoDB
import emiyaj.news.model.News
import emiyaj.util.EnvVariable
import org.litote.kmongo.getCollection

/**
 * This object provides utility functions for interacting with the news collection in the MongoDB database.
 * It uses the KMongo library for MongoDB operations.
 */
object NewsDatabase {
    // The news collection in the MongoDB database
    private val collection = MongoDB.getDatabase(EnvVariable.getMongoDatabase())?.getCollection<News>("news")

    /**
     * This function retrieves a list of news items from the news collection.
     * It allows for pagination by specifying the number of news items to retrieve and the number of news items to skip.
     *
     * @param length The number of news items to retrieve. Default is 5.
     * @param skip The number of news items to skip. Default is 0.
     * @return A list of news items, or an empty list if the collection is null.
     */
    fun getNews(length: Int = 5, skip: Int = 0) = collection?.find()?.limit(length)?.skip(skip)?.toList() ?: listOf()

    /**
     * This function inserts a news item into the news collection.
     *
     * @param news The news item to be inserted.
     */
    fun postNews(news: News) = collection?.insertOne(news)
}