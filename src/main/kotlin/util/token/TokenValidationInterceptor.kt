package emiyaj.util.token

import emiyaj.campsite.CampsiteController
import emiyaj.friendshippoints.FriendshipPointsController
import emiyaj.user.UserDatabase
import emiyaj.user.UserStatus
import emiyaj.util.EnvVariable
import emiyaj.util.UserSessionNew
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice(basePackageClasses = [FriendshipPointsController::class, CampsiteController::class])
class TokenValidationInterceptor : HandlerInterceptorAdapter() {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (EnvVariable.getVariable("env") == "debug") {
            request.setAttribute("user", UserDatabase.getUserByEmail("email@example.com"))
            return true
        }
        if (request.method.toUpperCase() == "OPTIONS") return true
        val token = request.getHeader("token")
        if (token == null) {
            response.writer.write(generateResponse(UserStatus.INVALID))
            response.contentType = "application/json"
            response.status = UserStatus.INVALID.httpCode
            return false
        }
        val result = UserSessionNew.authenticate(token, request.getHeader("Referer"))
        if (result.first != UserStatus.AUTHORIZED) {
            response.writer.write(generateResponse(result.first))
            response.contentType = "application/json"
            response.status = result.first.httpCode
            return false
        }
        request.setAttribute("user", result.second)
        return true
    }

    private fun generateResponse(code: UserStatus): String {
        return "{'code': '${code.code}', 'comment': '${code.comment}'}".replace("'", "\"")
    }
}