package emiyaj.comment

import emiyaj.common.CommonResult
import emiyaj.common.CommonStatus
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["comment"])
class PostController {
//    @PostMapping(value = ["/get"])
//    fun get(@RequestBody request: CommonRequest): CommonResult {
//        return CommonResult(CommonStatus.NORMAL, "success", Json.encodeToString(comments))
//    }
//

    @PostMapping(value = ["/post"])
    fun post(servlet: HttpServletRequest, @RequestBody request: PostRequest): CommonResult {
        //get user id from session
        val userId = servlet.session.getAttribute("userId") as Int // 유효하지 않은 코드. 재작성 필요.

        if (request.content.length < 4 || request.content.containsSpecial()) {
            return CommonResult(CommonStatus.NORMAL, "bad request")
        }
//       TODO PostDatabase.postToDatabase(userId , request.content, request)
        return CommonResult(CommonStatus.NORMAL, "success")
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