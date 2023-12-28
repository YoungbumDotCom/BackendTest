package emiyaj.user.model

/**
 * 이 데이터 클래스는 애플리케이션에서 사용자를 나타냅니다.
 * 사용자의 ID, 사용자 이름, 비밀번호, 이메일, 생성 시간을 포함합니다.
 *
 * @property id 사용자의 ID입니다. 이것은 사용자에 대한 고유 식별자입니다.
 * @property username 사용자의 사용자 이름입니다. 이것은 사용자를 식별하는 데 사용되는 이름입니다.
 * @property password 사용자의 비밀번호입니다. 이것은 사용자의 계정에 대한 액세스를 보호하는 데 사용됩니다.
 * @property email 사용자의 이메일입니다. 이것은 사용자에게 알림을 보내는 데 사용될 수 있습니다.
 * @property created 사용자가 생성된 시간입니다. 이것은 Unix 타임스탬프로 표현됩니다.
 */
data class User (
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val created: Long
)