package emiyaj.comment

import emiyaj.comment.model.Comment
import emiyaj.database.MongoDB
import emiyaj.util.EnvVariable
import org.litote.kmongo.eq
import org.litote.kmongo.getCollection
import java.util.*

/**
 * This object provides utility functions for interacting with the comments collection in the MongoDB database.
 * It uses the KMongo library for MongoDB operations.
 */
object CommentDatabase {
    // The comments collection in the MongoDB database
    private val collection = MongoDB.getDatabase(EnvVariable.getMongoDatabase())?.getCollection<Comment>("comments")

    /**
     * This function retrieves a list of comments for a villager by their code from the comments collection.
     *
     * @param code The code of the villager.
     * @return A list of comments for the villager, or an empty list if the collection is null.
     */
    fun getCommentsByVillagerCode(code: String): List<Comment> {
        return collection?.find(Comment::villagerCode eq code)?.toList() ?: return listOf()
    }

    /**
     * This function posts a comment for a villager by their code into the comments collection.
     * It creates a new Comment object with the given parameters and inserts it into the collection.
     *
     * @param code The code of the villager.
     * @param nickname The nickname of the user posting the comment.
     * @param ip The IP address of the user posting the comment.
     * @param comment The text of the comment.
     */
    fun postCommentByVillagerCode(code: String, nickname: String, ip: String, comment: String) {
        collection?.insertOne(Comment(nickname, ip, comment, Calendar.getInstance().timeInMillis, code))
    }
}