package emiyaj.user.model

import java.time.LocalDateTime

/**
 * 이 데이터 클래스는 멤버 데이터베이스의 사용자를 나타냅니다.
 * 사용자의 ID, 비밀번호, 이메일, 수정 시간, 전화번호, 텍스트, 검증, 생일, 이름, 별명, 생성 시간을 포함합니다.
 *
 * @property id 사용자의 ID입니다. 이것은 사용자의 고유 식별자입니다.
 * @property password 사용자의 비밀번호입니다. 이것은 사용자의 계정 접근을 보호하는 데 사용됩니다.
 * @property email 사용자의 이메일입니다. 이것은 사용자에게 알림을 보내는 데 사용할 수 있습니다.
 * @property modified 사용자가 수정된 시간입니다.
 * @property phone 사용자의 전화번호입니다.
 * @property text 사용자의 텍스트입니다.
 * @property verify 사용자의 검증입니다.
 * @property birth 사용자의 생일입니다.
 * @property name 사용자의 이름입니다.
 * @property nickname 사용자의 별명입니다.
 * @property created 사용자가 생성된 시간입니다. 이것은 LocalDateTime으로 표현됩니다.
 */
data class User (
    val id: Int,
    val password: String,
    val email: String,
    val modified: String,
    val phone: String,
    val text: String,
    val verify: String,
    val birth: String,
    val name: String,
    val nickname: String,
    val created: LocalDateTime
)