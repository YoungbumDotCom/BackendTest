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

@RestController
@RequestMapping(value = ["post"])
class PostController {
    @PostMapping(value = ["/post"])
    fun post(servlet: HttpServletRequest, @RequestBody request: PostRequest): CommonResult {
        //get user id from session
        val user = servlet.getAttribute("user") as? User
            ?: return CommonResult(CommonStatus.UNAUTHORIZED, "member.notLoggedIn") // 유효하지 않은 코드. 재작성 필요.
        //check if request is valid
        if (request.content.length < 4 || request.content.containsSpecial()) {
            return CommonResult(CommonStatus.NORMAL, "member.badContent")
        }

        val id = PostDatabase.postToDatabase(user.id , request.content, request.image)
        return if (id != -1)
            CommonResult(CommonStatus.NORMAL, "success", id)
        else
            CommonResult(CommonStatus.NORMAL, "member.postFailed")

    }

    @PostMapping(value = ["/get"])
    fun get(@RequestBody request: GetRequest): CommonResult {
        val found = PostDatabase.getPostsByRange(request.start, request.end)
        return CommonResult(CommonStatus.NORMAL, "success", Json.encodeToString(found))
    }

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