package emiyaj.comment

import emiyaj.comment.model.Comment
import emiyaj.database.MongoDB
import emiyaj.util.EnvVariable
import org.litote.kmongo.eq
import org.litote.kmongo.getCollection
import java.util.*

object CommentDatabase {
    private val collection = MongoDB.getDatabase(EnvVariable.getMongoDatabase())?.getCollection<Comment>("comments")

    fun getCommentsByVillagerCode(code: String): List<Comment> {
        return collection?.find(Comment::villagerCode eq code)?.toList() ?: return listOf()
    }

    fun postCommentByVillagerCode(code: String, nickname: String, ip: String, comment: String) {
        collection?.insertOne(Comment(nickname, ip, comment, Calendar.getInstance().timeInMillis, code))
    }
}