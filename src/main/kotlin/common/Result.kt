package emiyaj.common

import com.fasterxml.jackson.annotation.JsonInclude

/**
 * 이 인터페이스는 애플리케이션에서 결과를 나타냅니다.
 * 클라이언트 요청에 대한 응답의 공통 구조로 사용됩니다.
 * 코드, 코멘트, 그리고 선택적 데이터를 포함합니다.
 * @JsonInclude 어노테이션을 사용하여 이 타입의 객체의 JSON 직렬화에서 null 값이 포함되지 않도록 지정합니다.
 *
 * @property code 결과의 코드입니다. 이것은 각 결과 유형에 대한 고유 식별자입니다.
 * @property comment 결과의 코멘트입니다. 이것은 결과에 대한 사람이 읽을 수 있는 설명을 제공합니다.
 * @property data 결과와 관련된 데이터입니다. 이것은 선택적이며 기본값은 null입니다.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
interface Result {
    val code: String
    val comment: String
    val data: Any?
}