package emiyaj.campsite.model

import emiyaj.campsite.mongo.CampsiteTry

/**
 * Campsite content
 *
 * @property targetCode Target villager code.
 * @property done If complete task.
 * @property tries Any days appear villager
 * @since 1.0.0
 */
data class CampsiteContent (
        val targetCode: String,
        val done: Boolean,
        val tries: Array<CampsiteTry>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CampsiteContent

        if (targetCode != other.targetCode) return false
        if (!tries.contentEquals(other.tries)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = targetCode.hashCode()
        result = 31 * result + tries.contentHashCode()
        return result
    }
}