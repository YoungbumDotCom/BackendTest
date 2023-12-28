package emiyaj.comment

import emiyaj.comment.model.Post
import emiyaj.database.MySQL
import emiyaj.util.ImageStorage
import java.io.InputStream

object PostDatabase {
    /**
     * 이 함수는 데이터베이스에서 주어진 개수만큼의 최신 게시물을 검색합니다.
     * MySQL 데이터베이스에 연결하고, 게시물의 개수를 사용하여 SELECT SQL 문을 준비합니다.
     * SQL 문을 실행하고, 결과 세트에서 게시물의 ID, 작성자, 내용, 이미지 경로, 등록 날짜를 가져와 Post 객체를 생성하고 목록에 추가합니다.
     * SQL 문을 실행한 후, ResultSet, Statement, Connection을 닫습니다.
     *
     * @param count 검색할 게시물의 개수입니다.
     * @return 검색된 게시물의 목록을 반환합니다. 게시물이 존재하지 않는 경우 빈 목록을 반환합니다.
     */
    fun getPostByCount(count: Int): List<Post> {
        val connection = MySQL.getConnection()
        val statement = connection?.prepareStatement("SELECT * FROM post ORDER BY reg_date DESC LIMIT ?")
        statement?.setInt(1, count)
        val resultSet = statement?.executeQuery()
        val posts = mutableListOf<Post>()
        if (resultSet != null) {
            while (resultSet.next()) {
                posts.add(
                    Post(
                        resultSet.getInt("id"),
                        resultSet.getInt("author"),
                        resultSet.getString("content"),
                        resultSet.getString("image"),
                        resultSet.getLong("reg_date")
                    )
                )
            }
        }
        resultSet?.close()
        statement?.close()
        connection?.close()
        return posts
    }

    /**
     * 이 함수는 데이터베이스에서 주어진 범위의 게시물을 검색합니다.
     * MySQL 데이터베이스에 연결하고, 시작 인덱스와 끝 인덱스를 사용하여 SELECT SQL 문을 준비합니다.
     * SQL 문을 실행하고, 결과 세트에서 게시물의 ID, 작성자, 내용, 이미지 경로, 등록 날짜를 가져와 Post 객체를 생성하고 목록에 추가합니다.
     * SQL 문을 실행한 후, ResultSet, Statement, Connection을 닫습니다.
     *
     * @param start 검색할 게시물의 시작 인덱스입니다.
     * @param end 검색할 게시물의 끝 인덱스입니다.
     * @return 검색된 게시물의 목록을 반환합니다. 게시물이 존재하지 않는 경우 빈 목록을 반환합니다.
     */
    fun getPostsByRange(start: Int, end: Int): List<Post> {
        val connection = MySQL.getConnection()
        val statement = connection?.prepareStatement("SELECT * FROM post ORDER BY reg_date DESC LIMIT ?, ?")
        statement?.setInt(1, start)
        statement?.setInt(2, end)
        val resultSet = statement?.executeQuery()
        val posts = mutableListOf<Post>()
        if (resultSet != null) {
            while (resultSet.next()) {
                posts.add(
                    Post(
                        resultSet.getInt("id"),
                        resultSet.getInt("author"),
                        resultSet.getString("content"),
                        resultSet.getString("image"),
                        resultSet.getLong("reg_date")
                    )
                )
            }
        }
        resultSet?.close()
        statement?.close()
        connection?.close()
        return posts
    }

    /**
     * 이 함수는 주어진 ID에 해당하는 게시물을 데이터베이스에서 검색합니다.
     * MySQL 데이터베이스에 연결하고, ID를 사용하여 SELECT SQL 문을 준비합니다.
     * SQL 문을 실행하고, 결과 세트에서 게시물의 ID, 작성자, 내용, 이미지 경로, 등록 날짜를 가져와 Post 객체를 생성합니다.
     * SQL 문을 실행한 후, ResultSet, Statement, Connection을 닫습니다.
     *
     * @param id 검색할 게시물의 ID입니다.
     * @return ID에 해당하는 게시물을 나타내는 Post 객체, 또는 게시물이 존재하지 않는 경우 null을 반환합니다.
     */
    fun getPostById(id: Int): Post? {
        val connection = MySQL.getConnection()
        val statement = connection?.prepareStatement("SELECT * FROM post WHERE id = ?")
        statement?.setInt(1, id)
        val resultSet = statement?.executeQuery()
        resultSet?.next()
        val post = if (resultSet != null) {
            Post(
                resultSet.getInt("id"),
                resultSet.getInt("author"),
                resultSet.getString("content"),
                resultSet.getString("image"),
                resultSet.getLong("reg_date")
            )
        } else {
            null
        }

        resultSet?.close()
        statement?.close()
        connection?.close()
        return post
    }

    /**
     * 이 함수는 데이터베이스에 새로운 게시물을 추가합니다.
     * MySQL 데이터베이스에 연결하고, 게시물의 작성자, 내용, 이미지 경로를 사용하여 INSERT SQL 문을 준비합니다.
     * 현재 시간을 사용하여 게시물의 등록 날짜를 설정합니다.
     * SQL 문을 실행한 후, Statement와 Connection을 닫습니다.
     *
     * @param author 게시물의 작성자입니다. 이것은 게시물을 작성한 사용자의 ID를 나타냅니다.
     * @param content 게시물의 내용입니다. 이것은 게시물의 본문을 나타냅니다.
     * @param imageKey 게시물의 이미지 키입니다. 이것은 게시물에 첨부된 이미지의 키를 나타냅니다.
     */
    fun postToDatabase(author: Int, content: String, image: InputStream) {
        ImageStorage.uploadImage(author, image)
        val connection = MySQL.getConnection()
        val statement =
            connection?.prepareStatement("INSERT INTO post (author, content, image, reg_date) VALUES (?, ?, ?, ?)")
        statement?.setInt(1, author)
        statement?.setString(2, content)
        statement?.setString(3, imageKey)
        statement?.setLong(4, System.currentTimeMillis())
        statement?.executeUpdate()
        statement?.close()
        connection?.close()
    }
}