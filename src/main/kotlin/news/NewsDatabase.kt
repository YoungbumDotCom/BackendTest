package emiyaj.news

import emiyaj.database.MongoDB
import emiyaj.news.model.News
import emiyaj.util.EnvVariable
import org.litote.kmongo.getCollection

object NewsDatabase {
    private val collection = MongoDB.getDatabase(EnvVariable.getMongoDatabase())?.getCollection<News>("news")

    fun getNews(length: Int = 5, skip: Int = 0) = collection?.find()?.limit(length)?.skip(skip)?.toList() ?: listOf()

    fun postNews(news: News) = collection?.insertOne(news)
}