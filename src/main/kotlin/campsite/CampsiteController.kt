package emiyaj.campsite

import com.beust.klaxon.Klaxon
import emiyaj.campsite.model.CampsiteContent
import emiyaj.user.model.User
import emiyaj.common.CommonStatus
import emiyaj.common.CommonRequest
import org.springframework.web.bind.annotation.*

/**
 * Campsite Spring Boot Controller
 */
@RestController
@RequestMapping(value = ["/campsite"])
class CampsiteController {
//@RequestAttribute("user") user: User
    /**
     * Get all the campsite content of the user.
     *
     *
     */
    @GetMapping(value = ["/"])
    fun get(@RequestAttribute("user") user: User): CampsiteResult {
        val data = CampsiteDatabase.getContent(user) ?: return CampsiteResult.badRequest()
        return CampsiteResult.successWithEncrypt(Klaxon().toJsonString(data))
    }
/*
    @PostMapping(value = ["/"])
    fun put(@RequestAttribute("user") user: User, @RequestBody body: CommonRequest): CampsiteResult {
        val data = Klaxon().parseArray<CampsiteContent>(body.decryptedData) ?: return CampsiteResult.badRequest()
        CampsiteDatabase.overwriteContent(user, data)
        return CampsiteResult(CommonStatus.NORMAL, "success")
    }

 */
}