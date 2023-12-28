package emiyaj.user

/**
 * 이 열거형 클래스는 애플리케이션의 사용자 상태를 나타냅니다.
 * 각 상태는 코드, 설명, 그리고 관련 HTTP 코드를 가지고 있습니다.
 *
 * @property code 사용자 상태의 코드입니다. 이것은 각 상태에 대한 고유 식별자입니다.
 * @property comment 사용자 상태의 설명입니다. 이것은 상태에 대한 사람이 읽을 수 있는 설명을 제공합니다.
 * @property httpCode 사용자 상태와 관련된 HTTP 코드입니다. 이것은 HTTP 응답에서 사용됩니다.
 */
enum class UserStatus(val code: String, val comment: String, val httpCode: Int) {
    /**
     * AUTHORIZED 상태는 행동을 수행할 권한이 있는 사용자를 나타냅니다.
     * 이것은 HTTP 요청이 성공적이라는 것을 나타내는 200의 HTTP 코드를 가지고 있습니다.
     */
    AUTHORIZED("user00", "Authorized", 200),

    /**
     * INVALID 상태는 예를 들어 잘못된 자격 증명으로 인해 유효하지 않은 사용자를 나타냅니다.
     * 이것은 금지된 HTTP 요청을 나타내는 403의 HTTP 코드를 가지고 있습니다.
     */
    INVALID("user01", "Invalid", 403),

    /**
     * EXPIRED 상태는 세션 또는 토큰이 만료된 사용자를 나타냅니다.
     * 이것은 HTTP 요청이 성공적이라는 것을 나타내는 200의 HTTP 코드를 가지고 있습니다. 이 상태의 실제 처리는 애플리케이션 로직에서 수행됩니다.
     */
    EXPIRED("user02", "Expired", 200),

    /**
     * NOT_ALLOWED 상태는 권한 부족으로 인해 행동을 수행할 수 없는 사용자를 나타냅니다.
     * 이것은 잘못된 HTTP 요청을 나타내는 400의 HTTP 코드를 가지고 있습니다.
     */
    NOT_ALLOWED("user03", "Not Allowed", 400),

    /**
     * CREATED 상태는 방금 생성된 사용자를 나타냅니다.
     * 이것은 HTTP 요청이 성공적이라는 것을 나타내는 200의 HTTP 코드를 가지고 있습니다.
     */
    CREATED("user04", "Created", 200)
}