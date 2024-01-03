package emiyaj.user

import kotlinx.serialization.Serializable

/**
 * 사용자 로그인 요청을 나타내는 데이터 클래스입니다.
 *
 * @property email 사용자의 이메일 주소입니다.
 * @property password 사용자의 비밀번호입니다.
 */
@Serializable
data class LoginRequest (
    val email: String,     // 사용자의 이메일 주소
    val password: String   // 사용자의 비밀번호
)