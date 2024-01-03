package emiyaj.user

import kotlinx.serialization.Serializable

/**
 * 사용자 로그아웃 (세션 파괴) 요청을 나타내는 데이터 클래스입니다.
 *
 * @property email 기로그인 사용자의 토큰

 */
@Serializable
data class LogoutRequest (
     val token: String     // 기로그인 사용자의 토큰
)