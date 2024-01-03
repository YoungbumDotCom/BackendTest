package emiyaj.user

import emiyaj.common.CommonResult
import emiyaj.common.CommonStatus
import emiyaj.common.UserResult
import emiyaj.util.UserSessionNew
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["user"])
class UserController {
    @RequestMapping(value = ["login"], method = [RequestMethod.POST])
    fun loginV2(@RequestBody req: LoginRequest): UserResult {
        val user = UserDatabase.authenticate(req.email, req.password) ?: return UserResult(UserStatus.INVALID, "invalid")
        val token = UserSessionNew.createSession(user)
        return UserResult(UserStatus.CREATED, "success", token)
    }

    @RequestMapping(value = ["logout"], method = [RequestMethod.POST])
    fun logout(@RequestBody req: LogoutRequest): CommonResult {
        val token = req.token
        UserSessionNew.invalidate(token)
        return CommonResult(CommonStatus.NORMAL, "success")
    }

    @RequestMapping(value = ["register"], method = [RequestMethod.POST])
    fun register(@RequestBody req: RegisterRequest): CommonResult {
        val user = UserDatabase.createUser(req.email, req.password, req.name, req.nickname)
            ?: return CommonResult(CommonStatus.CRITICAL, "login.registerError")
        val token = UserSessionNew.createSession(user)
        return CommonResult(CommonStatus.NORMAL, "success")
    }
}
