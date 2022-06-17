package emiyaj.util

object EnvVariable {
    private val env: Map<String, String> = System.getenv()

    fun getVariable(key: String): String? = env[key]

    fun getMongoDatabase(): String = getVariable("mongo_database") ?: "test"

    fun doesExists(key: String): Boolean = env[key] != null
}