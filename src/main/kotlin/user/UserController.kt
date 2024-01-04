package emiyaj.user

import emiyaj.common.CommonResult
import emiyaj.common.CommonStatus
import emiyaj.common.UserResult
import emiyaj.util.UserSessionNew
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
/**
 * `@RestController` 어노테이션이 붙은 클래스로, 클라이언트로부터의 HTTP 요청을 처리하는 컨트롤러입니다.
 * `@RequestMapping` 어노테이션이 붙어 있어 "/user" 경로로 들어오는 요청을 처리합니다.
 */
@RestController
@RequestMapping(value = ["user"])
class UserController {
    /**
     * `@RequestMapping` 어노테이션이 붙은 메서드로, 클라이언트로부터 POST 요청을 받아 로그인을 처리합니다.
     * 요청 경로는 "/user/login"입니다.
     *
     * @param req 클라이언트로부터 받은 로그인 요청 정보를 담고 있는 LoginRequest 객체입니다.
     * @return 로그인 처리 결과를 나타내는 UserResult 객체를 반환합니다. 로그인이 성공하면 생성된 세션 토큰을 반환합니다.
     */
    @RequestMapping(value = ["login"], method = [RequestMethod.POST])
    fun loginV2(@RequestBody req: LoginRequest): UserResult {
        val user =
            UserDatabase.authenticate(req.email, req.password) ?: return UserResult(UserStatus.INVALID, "invalid")
        val token = UserSessionNew.createSession(user)
        return UserResult(UserStatus.CREATED, "success", token)
    }

    /**
     * `@RequestMapping` 어노테이션이 붙은 메서드로, 클라이언트로부터 POST 요청을 받아 로그아웃을 처리합니다.
     * 요청 경로는 "/user/logout"입니다.
     *
     * @param req 클라이언트로부터 받은 로그아웃 요청 정보를 담고 있는 LogoutRequest 객체입니다.
     * @return 로그아웃 처리 결과를 나타내는 CommonResult 객체를 반환합니다.
     */
    @RequestMapping(value = ["logout"], method = [RequestMethod.POST])
    fun logout(@RequestBody req: LogoutRequest): CommonResult {
        val token = req.token
        UserSessionNew.invalidate(token)
        return CommonResult(CommonStatus.NORMAL, "success")
    }

    /**
     * `@RequestMapping` 어노테이션이 붙은 메서드로, 클라이언트로부터 POST 요청을 받아 회원가입을 처리합니다.
     * 요청 경로는 "/user/register"입니다.
     *
     * @param req 클라이언트로부터 받은 회원가입 요청 정보를 담고 있는 RegisterRequest 객체입니다.
     * @return 회원가입 처리 결과를 나타내는 CommonResult 객체를 반환합니다. 회원가입이 성공하면 생성된 세션 토큰을 반환합니다.
     */
    @RequestMapping(value = ["register"], method = [RequestMethod.POST])
    fun register(@RequestBody req: RegisterRequest): CommonResult {
        val user = UserDatabase.createUser(req.email, req.password, req.name, req.nickname)
            ?: return CommonResult(CommonStatus.CRITICAL, "login.registerError")
        val token = UserSessionNew.createSession(user)
        return CommonResult(CommonStatus.NORMAL, "success")
    }
}
