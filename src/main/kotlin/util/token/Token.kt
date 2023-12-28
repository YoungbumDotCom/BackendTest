package emiyaj.util.token

/**
 * 이 데이터 클래스는 애플리케이션의 토큰을 나타냅니다.
 * 토큰 헤더, 토큰이 발행된 엔티티, 토큰 문자열 자체,
 * 그리고 토큰의 만료, 생성, 정리 시간에 대한 타임스탬프를 포함합니다.
 *
 * @property header 토큰의 헤더로, TokenHeader 객체로 표현됩니다.
 * @property issuedFor 토큰이 발행된 엔티티입니다. 이는 사용자 ID, 사용자 이름 등이 될 수 있습니다.
 * @property token 토큰 문자열 자체입니다. 이는 일반적으로 Base64로 인코딩된 문자열입니다.
 * @property expireTime 토큰의 만료 시간으로, Unix 타임스탬프로 표현됩니다.
 * @property createdTime 토큰의 생성 시간으로, Unix 타임스탬프로 표현됩니다.
 * @property cleanTime 토큰의 정리 시간으로, Unix 타임스탬프로 표현됩니다. 이는 일반적으로 토큰이 메모리나 데이터베이스에서 제거되어야 하는 시간입니다.
 */
data class Token (
        val header: TokenHeader,
        val issuedFor: String,
        val token: String,
        val expireTime: Long,
        val createdTime: Long,
        val cleanTime: Long
)