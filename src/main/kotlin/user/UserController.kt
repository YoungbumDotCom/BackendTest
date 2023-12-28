package emiyaj.user

import com.beust.klaxon.Klaxon
import emiyaj.common.CommonResult
import emiyaj.common.CommonStatus
import emiyaj.common.UserResult
import emiyaj.util.UserSession
import emiyaj.util.UserSessionNew
import kotlinx.serialization.json.Json
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["admin"])
class UserController {
    @RequestMapping(value = ["login"], method = [RequestMethod.POST])
    fun login(@RequestBody req: LoginRequest): CommonResult {
        val credential = Json.decodeFromString<LoginBody>(req.decryptedData) ?: return CommonResult(CommonStatus.INTERNAL_ERROR, "")
        val user = UserDatabase.authenticate(credential.username, credential.password) ?: return CommonResult(CommonStatus.INTERNAL_ERROR, "")
        val token = UserSession.createSession(user)
        return CommonResult(status = CommonStatus.NORMAL, comment = "success", data = "{'token': '$token'}".replace("'", "\""))
    }

    @RequestMapping(value = ["login"], method = [RequestMethod.POST])
    fun loginV2(@RequestBody req: LoginRequest): UserResult {
        val credential = Json.decodeFromString<LoginBody>(req.decryptedData) ?: return UserResult(UserStatus.INVALID, "invalid")
        val user = UserDatabase.authenticate(credential.username, credential.password) ?: return UserResult(UserStatus.INVALID, "invalid")
        val token = UserSessionNew.createSession(user)
        return UserResult(UserStatus.CREATED, "success", token)
    }
}
