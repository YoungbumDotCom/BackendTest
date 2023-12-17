package emiyaj.util

/**
 * This object provides utility functions for managing environment variables.
 * It retrieves the environment variables from the System.getenv() function and stores them in a private map.
 */
object EnvVariable {
    // Map of environment variables
    private val env: Map<String, String> = System.getenv()

    /**
     * This function retrieves the value of an environment variable given its key.
     *
     * @param key The key of the environment variable.
     * @return The value of the environment variable, or null if it does not exist.
     */
    fun getVariable(key: String): String? = env[key]

    /**
     * This function retrieves the value of the "mongo_database" environment variable.
     * If the variable does not exist, it returns "test" as a default value.
     *
     * @return The value of the "mongo_database" environment variable, or "test" if it does not exist.
     */
    fun getMongoDatabase(): String = getVariable("mongo_database") ?: "test"

    /**
     * This function checks if an environment variable exists given its key.
     *
     * @param key The key of the environment variable.
     * @return True if the environment variable exists, false otherwise.
     */
    fun doesExists(key: String): Boolean = env[key] != null
}