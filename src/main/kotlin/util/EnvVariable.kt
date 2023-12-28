package emiyaj.util

/**
 * 이 객체는 환경 변수를 관리하기 위한 유틸리티 함수를 제공합니다.
 * System.getenv() 함수에서 환경 변수를 검색하고 이를 개인 맵에 저장합니다.
 */
object EnvVariable {
    // 환경 변수의 맵
    private val env: Map<String, String> = System.getenv()

    /**
     * 이 함수는 주어진 키의 환경 변수 값을 검색합니다.
     *
     * @param key 환경 변수의 키입니다.
     * @return 환경 변수의 값, 또는 존재하지 않는 경우 null을 반환합니다.
     */
    fun getVariable(key: String): String? = env[key]

    /**
     * 이 함수는 "mongo_database" 환경 변수의 값을 검색합니다.
     * 변수가 존재하지 않는 경우 기본값으로 "test"를 반환합니다.
     *
     * @return "mongo_database" 환경 변수의 값, 또는 존재하지 않는 경우 "test"를 반환합니다.
     */
    fun getMongoDatabase(): String = getVariable("mongo_database") ?: "test"

    /**
     * 이 함수는 주어진 키의 환경 변수가 존재하는지 확인합니다.
     *
     * @param key 환경 변수의 키입니다.
     * @return 환경 변수가 존재하면 true, 그렇지 않으면 false를 반환합니다.
     */
    fun doesExists(key: String): Boolean = env[key] != null
}