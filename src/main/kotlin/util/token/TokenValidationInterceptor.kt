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

/**
 * This class intercepts HTTP requests and validates the token in the request header.
 * It is annotated with @ControllerAdvice to be automatically picked up by Spring.
 * It applies to the controllers specified in the basePackageClasses attribute.
 */
@ControllerAdvice(basePackageClasses = [FriendshipPointsController::class, CampsiteController::class])
class TokenValidationInterceptor : HandlerInterceptorAdapter() {

    /**
     * This function is called before the request is handled.
     * It checks the token in the request header and sets the user attribute in the request if the token is valid.
     *
     * @param request The HttpServletRequest object associated with the incoming request.
     * @param response The HttpServletResponse object associated with the response to the request.
     * @param handler The handler object chosen to execute the request.
     * @return True if the request should be handled, false otherwise.
     */
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // If the environment variable "env" is set to "debug", set the user attribute in the request and return true
        if (EnvVariable.getVariable("env") == "debug") {
            request.setAttribute("user", UserDatabase.getUserByEmail("email@example.com"))
            return true
        }
        // If the request method is OPTIONS, return true
        if (request.method.uppercase() == "OPTIONS") return true
        // Get the token from the request header
        val token = request.getHeader("token")
        // If the token is null, write a response with the INVALID user status and return false
        if (token == null) {
            response.writer.write(generateResponse(UserStatus.INVALID))
            response.contentType = "application/json"
            response.status = UserStatus.INVALID.httpCode
            return false
        }
        // Authenticate the token
        val result = UserSessionNew.authenticate(token, request.getHeader("Referer"))
        // If the user status is not AUTHORIZED, write a response with the user status and return false
        if (result.first != UserStatus.AUTHORIZED) {
            response.writer.write(generateResponse(result.first))
            response.contentType = "application/json"
            response.status = result.first.httpCode
            return false
        }
        // Set the user attribute in the request and return true
        request.setAttribute("user", result.second)
        return true
    }

    /**
     * This function generates a JSON response given a UserStatus.
     *
     * @param code The UserStatus to be included in the response.
     * @return The JSON response as a string.
     */
    private fun generateResponse(code: UserStatus): String {
        return "{'code': '${code.code}', 'comment': '${code.comment}'}".replace("'", "\"")
    }
}