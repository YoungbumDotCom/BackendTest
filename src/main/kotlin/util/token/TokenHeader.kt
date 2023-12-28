package emiyaj.util.token

/**
 * 이 데이터 클래스는 애플리케이션의 토큰 헤더를 나타냅니다.
 * 토큰에 사용된 알고리즘을 포함합니다. 기본 알고리즘은 "AES256"입니다.
 *
 * @property algorithm 토큰에 사용된 알고리즘입니다. 기본값은 "AES256"입니다.
 */
data class TokenHeader (
        val algorithm: String = "AES256"
)