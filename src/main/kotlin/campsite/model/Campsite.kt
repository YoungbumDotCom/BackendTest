package emiyaj.campsite.model

/**
 * Campsite Model
 *
 * @property uid ObjectId of user.
 * @property content Array of Campsite content (tries)
 */
data class Campsite (
        val uid: String,
        val content: List<CampsiteContent>
)