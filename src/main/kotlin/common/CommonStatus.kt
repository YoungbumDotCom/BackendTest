package emiyaj.common

/**
 * 이 열거형 클래스는 애플리케이션에서 공통 작업의 상태를 나타냅니다.
 * 각 상태는 관련된 상태 텍스트를 가지고 있습니다.
 *
 * @property statusText 공통 상태의 상태 텍스트입니다. 이것은 각 상태에 대한 고유 식별자입니다.
 */
enum class CommonStatus(val statusText: String) {
    /**
     * NORMAL 상태는 성공적인 작업을 나타냅니다.
     */
    NORMAL("emiyaj00"),

    /**
     * WARNING 상태는 경고와 함께 완료된 작업을 나타냅니다.
     */
    WARNING("emiyaj01"),

    /**
     * CRITICAL 상태는 작업에서 중대한 실패를 나타냅니다.
     */
    CRITICAL("emiyaj02"),

    /**
     * NOT_OPERATIONAL 상태는 시스템 또는 구성 요소가 작동하지 않아 작업을 수행할 수 없음을 나타냅니다.
     */
    NOT_OPERATIONAL("emiyaj03"),

    /**
     * INTERNAL_ERROR 상태는 공개할 수 없는 내부 오류를 나타냅니다.
     */
    INTERNAL_ERROR("emiyaj04"),

    /**
     * UNAUTHORIZED 상태는 권한 부족으로 인해 실패한 작업을 나타냅니다.
     */
    UNAUTHORIZED("emiyaj05")
}