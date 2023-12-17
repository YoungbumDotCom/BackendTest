package emiyaj.comment

import com.beust.klaxon.Klaxon
import emiyaj.common.CommonRequest
import emiyaj.common.CommonResult
import emiyaj.common.CommonStatus
import emiyaj.util.AESEncryption.encrypt
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
 * This class handles comment-related HTTP requests.
 * It is annotated with @RestController, indicating that it is a Spring MVC controller that returns data directly in the response body.
 * It is also annotated with @RequestMapping at the class level, specifying that all methods in this class handle requests to the "/comment" path.
 */
@RestController
@RequestMapping(value = ["comment"])
class CommentController {

    /**
     * This function handles POST requests to the "/get" path.
     * It retrieves comments for a villager by their code from the request body and returns them in a CommonResult object.
     *
     * @param request The request body, represented by a CommonRequest object.
     * @return A CommonResult object containing the retrieved comments.
     */
    @PostMapping(value = ["/get"])
    fun get(@RequestBody request: CommonRequest): CommonResult {
        val code = request.decryptedData
        val comments = CommentDatabase.getCommentsByVillagerCode(code)
        return CommonResult(CommonStatus.NORMAL, "success", encrypt(Klaxon().toJsonString(comments)))
    }

    /**
     * This function checks if a string contains any special characters.
     * It is used to validate the nickname and comment in the post function.
     *
     * @param str The string to check.
     * @return True if the string contains any special characters, false otherwise.
     */
    private fun containsSpecial(str: String): Boolean {
        val forbiddenCharacters = listOf('\'', '"', '<', '>', '\\', '{', '}')
        for (char in forbiddenCharacters) {
            if (str.contains(char)) {
                return true
            }
        }
        return false
    }

    /**
     * This function handles POST requests to the "/post" path.
     * It posts a comment for a villager by their code, with a nickname and comment received in the request body.
     * It validates the nickname and comment before posting the comment.
     *
     * @param servlet The HttpServletRequest object associated with the incoming request.
     * @param request The request body, represented by a PostRequest object.
     * @return A CommonResult object representing the result of the operation.
     */
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