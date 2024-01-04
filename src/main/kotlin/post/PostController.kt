package emiyaj.comment

import emiyaj.common.CommonResult
import emiyaj.common.CommonStatus
import emiyaj.post.GetRequest
import emiyaj.user.model.User
import jakarta.servlet.http.HttpServletRequest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayInputStream
import java.io.InputStream
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * `@RestController` 어노테이션이 붙은 클래스로, 클라이언트로부터의 HTTP 요청을 처리하는 컨트롤러입니다.
 * `@RequestMapping` 어노테이션이 붙어 있어 "/post" 경로로 들어오는 요청을 처리합니다.
 */
@RestController
@RequestMapping(value = ["post"])
class PostController {
    /**
     * `@PostMapping` 어노테이션이 붙은 메서드로, 클라이언트로부터 POST 요청을 받아 처리합니다.
     * 요청 경로는 "/post"입니다.
     *
     * @param servlet 클라이언트의 HTTP 요청 정보를 담고 있는 HttpServletRequest 객체입니다.
     * @param request 클라이언트로부터 받은 요청 본문을 PostRequest 객체로 변환한 것입니다.
     * @return 요청 처리 결과를 나타내는 CommonResult 객체를 반환합니다.
     */
    @PostMapping(value = ["/post"])
    fun post(servlet: HttpServletRequest, @RequestBody request: PostRequest): CommonResult {
        // 세션에서 사용자 ID를 가져옵니다.
        val user = servlet.getAttribute("user") as? User
            ?: return CommonResult(
                CommonStatus.UNAUTHORIZED,
                "member.notLoggedIn"
            ) // 로그인하지 않은 사용자는 UNAUTHORIZED 상태와 함께 처리를 종료합니다.

        // 요청이 유효한지 확인합니다. 요청 내용의 길이가 4 미만이거나 특수 문자를 포함하고 있다면 요청을 거부합니다.
        if (request.content.length < 4 || request.content.containsSpecial()) {
            return CommonResult(CommonStatus.NORMAL, "member.badContent")
        }

        // 요청을 데이터베이스에 저장하고, 저장된 게시물의 ID를 가져옵니다.
        val id = PostDatabase.postToDatabase(user.id, request.content, request.image)

        // 게시물 저장이 성공적으로 이루어졌다면 NORMAL 상태와 함께 게시물 ID를 반환하고, 그렇지 않다면 실패 메시지와 함께 처리를 종료합니다.
        return if (id != -1)
            CommonResult(CommonStatus.NORMAL, "success", id)
        else
            CommonResult(CommonStatus.NORMAL, "member.postFailed")
    }

    /**
     * `@PostMapping` 어노테이션이 붙은 메서드로, 클라이언트로부터 POST 요청을 받아 처리합니다.
     * 요청 경로는 "/get"입니다.
     *
     * @param request 클라이언트로부터 받은 요청 본문을 GetRequest 객체로 변환한 것입니다.
     * @return 요청 처리 결과를 나타내는 CommonResult 객체를 반환합니다. 결과는 범위 내의 게시물을 JSON 형식으로 인코딩한 문자열입니다.
     */
    @PostMapping(value = ["/get"])
    fun get(@RequestBody request: GetRequest): CommonResult {
        val found = PostDatabase.getPostsByRange(request.start, request.end)
        return CommonResult(CommonStatus.NORMAL, "success", Json.encodeToString(found))
    }

    /**
     * 문자열이 특수 문자를 포함하고 있는지 확인하는 함수입니다.
     * 특수 문자는 작은따옴표('), 큰따옴표("), 부등호(<, >), 역슬래시(\), 중괄호({, })를 포함합니다.
     *
     * @return 문자열에 특수 문자가 포함되어 있으면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
     */
    private fun String.containsSpecial(): Boolean {
        val forbiddenCharacters = listOf('\'', '"', '<', '>', '\\', '{', '}')
        for (char in forbiddenCharacters) {
            if (this.contains(char)) {
                return true
            }
        }
        return false
    }
}