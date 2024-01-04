package emiyaj.comment

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * `PostRequest`는 클라이언트로부터 받은 게시물 요청 정보를 담는 데이터 클래스입니다.
 * 이 클래스는 Jackson 라이브러리의 `@JsonCreator` 어노테이션을 사용하여 JSON 형식의 요청 본문을 객체로 변환합니다.
 *
 * @property image 클라이언트로부터 받은 이미지 정보를 담는 문자열입니다. 이 정보는 Base64로 인코딩된 이미지 데이터입니다.
 * @property content 클라이언트로부터 받은 게시물 내용을 담는 문자열입니다.
 */
data class PostRequest @JsonCreator constructor(
    @JsonProperty val image: String,
    @JsonProperty val content: String,
)