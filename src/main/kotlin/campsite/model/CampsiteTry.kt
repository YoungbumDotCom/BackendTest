package emiyaj.campsite.mongo

/**
 * Campsite Try (Every villager visit)
 *
 * @property code Visitor's villager code
 * @property personality Visitor's personality (for specific personality tries)
 * @property month Month of date of visit.
 * @property day Day of date of visit
 * @property year Year of date of visit
 */
data class CampsiteTry (
        val code: String,
        val personality: String,
        val month: Int,
        val day: Int,
        val year: Int
)