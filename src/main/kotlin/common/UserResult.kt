package emiyaj.common

import com.fasterxml.jackson.annotation.JsonIgnore
import emiyaj.common.Result
import emiyaj.user.UserStatus

/**
 * 이 데이터 클래스는 애플리케이션에서 사용자 특정 결과를 나타냅니다.
 * Result 인터페이스를 확장하고 UserStatus, 코멘트, 그리고 선택적 데이터를 포함합니다.
 * UserStatus는 이 타입의 객체의 JSON 직렬화에서 무시됩니다.
 *
 * @property status 결과의 UserStatus입니다. 이것은 사용자 특정 상태 코드를 제공합니다.
 * @property comment 결과의 코멘트입니다. 이것은 결과에 대한 사람이 읽을 수 있는 설명을 제공합니다.
 * @property data 결과와 관련된 데이터입니다. 이것은 선택적이며 기본값은 null입니다.
 */
data class UserResult(
        @JsonIgnore val status: UserStatus,
        override val comment: String,
        override val data: Any? = null

) : Result {
    /**
     * 이 속성은 Result 인터페이스의 코드 속성을 재정의합니다.
     * 결과의 UserStatus에서 코드를 검색합니다.
     */
    override val code: String
        get() = status.code
}