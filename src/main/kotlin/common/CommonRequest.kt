package emiyaj.common

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 이 클래스는 애플리케이션에서 공통적으로 사용되는 요청을 나타냅니다.
 * 요청에서 받은 암호화된 데이터인 'data' 속성을 포함하고 있습니다.
 *
 * @property data 요청에서 받은 암호화된 데이터입니다.
 *
 * @constructor 새로운 'CommonRequest' 객체를 생성합니다.
 * @JsonCreator 어노테이션은 이 생성자가 JSON 역직렬화에 사용되어야 함을 나타냅니다.
 * 'data' 매개변수는 @JsonProperty 어노테이션이 붙어 있어 JSON 속성 바인딩에 사용되어야 함을 나타냅니다.
 *
 * @param data 암호화된 요청 데이터입니다.
 */
class CommonRequest @JsonCreator constructor(@JsonProperty val data: String)