package emiyaj.user

import kotlinx.serialization.Serializable

/**
 * 사용자 등록 요청을 나타내는 데이터 클래스입니다.
 *
 * @property email 사용자의 이메일 주소입니다.
 * @property password 사용자의 비밀번호입니다.
 * @property name 사용자의 실제 이름입니다.
 * @property nickname 사용자의 별명입니다.
 */
@Serializable
data class RegisterRequest (
     val email: String,     // 사용자의 이메일 주소
     val password: String,  // 사용자의 비밀번호
     val name: String,      // 사용자의 실제 이름
     val nickname: String   // 사용자의 별명
)