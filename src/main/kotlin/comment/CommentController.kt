package emiyaj.comment

import com.beust.klaxon.Klaxon
import emiyaj.comment.model.Comment
import emiyaj.common.CommonRequest
import emiyaj.common.CommonResult
import emiyaj.common.CommonStatus
import emiyaj.util.AESEncryption.encrypt
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = ["comment"])
class CommentController {
    @PostMapping(value = ["/get"])
    fun get(@RequestBody request: CommonRequest): CommonResult {
        val code = request.decryptedData
        val comments = CommentDatabase.getCommentsByVillagerCode(code)
        return CommonResult(CommonStatus.NORMAL, "success", encrypt(Klaxon().toJsonString(comments)))
    }

    private fun containsSpecial(str: String): Boolean {
        val forbiddenCharacters = listOf('\'', '"', '<', '>', '\\', '{', '}')
        for (char in forbiddenCharacters) {
            if (str.contains(char)) {
                return true
            }
        }
        return false
    }

    @PostMapping(value = ["/post"])
    fun post(servlet: HttpServletRequest, @RequestBody request: PostRequest): CommonResult {
        val ip = servlet.remoteAddr
        if (request.nickname.length < 4 || request.comment.length < 4 || containsSpecial(request.nickname) || containsSpecial(request.comment)) {
            return CommonResult(CommonStatus.NORMAL, "bad request")
        }
        CommentDatabase.postCommentByVillagerCode(request.code, request.nickname, ip, request.comment)
        return CommonResult(CommonStatus.NORMAL, "success")
    }
}