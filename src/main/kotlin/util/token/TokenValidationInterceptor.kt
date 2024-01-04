package emiyaj.util.token

import emiyaj.comment.PostController
import emiyaj.user.UserController
import emiyaj.user.UserDatabase
import emiyaj.user.UserStatus
import emiyaj.util.EnvVariable
import emiyaj.util.UserSessionNew
import org.springframework.web.bind.annotation.ControllerAdvice
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor

/**
 * 이 클래스는 HTTP 요청을 가로채고 요청 헤더의 토큰을 검증합니다.
 * @ControllerAdvice 어노테이션이 붙어 있어 Spring에서 자동으로 인식됩니다.
 * basePackageClasses 속성에 지정된 컨트롤러에 적용됩니다.
 */
@ControllerAdvice(basePackageClasses = [PostController::class])
class TokenValidationInterceptor : HandlerInterceptor {

    /**
     * 이 함수는 요청이 처리되기 전에 호출됩니다.
     * 요청 헤더의 토큰을 확인하고 토큰이 유효하면 요청의 사용자 속성을 설정합니다.
     *
     * @param request 들어오는 요청과 연관된 HttpServletRequest 객체입니다.
     * @param response 요청에 대한 응답과 연관된 HttpServletResponse 객체입니다.
     * @param handler 요청을 실행하기 위해 선택된 핸들러 객체입니다.
     * @return 요청이 처리되어야 하면 true, 그렇지 않으면 false를 반환합니다.
     */
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // 환경 변수 "env"가 "debug"로 설정되어 있으면 사용자 속성을 강제 설정하고 true를 반환합니다.
        if (EnvVariable.getVariable("env") == "debug") {
            request.setAttribute("user", UserDatabase.getUserByEmail("admin@admin.com"))
            return true
        }
        // 요청 메서드가 OPTIONS인 경우 true를 반환합니다.
        if (request.method.uppercase() == "OPTIONS") return true
        // 요청 헤더에서 토큰을 가져옵니다.
        val token = request.getHeader("Authorization")
        // 토큰이 null인 경우, INVALID 사용자 상태로 응답을 작성하고 false를 반환합니다.
        if (token == null) {
            response.writer.write(generateResponse(UserStatus.INVALID))
            response.contentType = "application/json"
            response.status = UserStatus.INVALID.httpCode
            return false
        }
        // 토큰을 인증합니다.
        val result = UserSessionNew.authenticate(token, request.getHeader("Referer"))
        // 사용자 상태가 AUTHORIZED가 아닌 경우, 사용자 상태로 응답을 작성하고 false를 반환합니다.
        if (result.first != UserStatus.AUTHORIZED) {
            response.writer.write(generateResponse(result.first))
            response.contentType = "application/json"
            response.status = result.first.httpCode
            return false
        }
        // 요청의 사용자 속성을 설정하고 true를 반환합니다.
        request.setAttribute("user", result.second)
        return true
    }

    /**
     * 이 함수는 UserStatus를 주어진 JSON 응답을 생성합니다.
     *
     * @param code 응답에 포함될 UserStatus입니다.
     * @return 문자열로 된 JSON 응답입니다.
     */
    private fun generateResponse(code: UserStatus): String {
        return "{'code': '${code.code}', 'comment': '${code.comment}'}".replace("'", "\"")
    }
}