package emiyaj.friendshippoint

/**
 * This is a data class that represents the body of a request to add friendship points.
 * It is used in the Friendship Point module of the application.
 *
 * @property id The ID of the user to whom the points are to be added. This is passed in the request body.
 * @property code The code of the friendship point event. This is passed in the request body.
 * @property point The number of points to be added. This is passed in the request body.
 */
data class AddFriendshipPointBody (
        val id: String,
        val code: String,
        val point: Int
)