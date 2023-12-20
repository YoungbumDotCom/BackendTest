package emiyaj.campsite

import com.beust.klaxon.Klaxon
import emiyaj.campsite.model.CampsiteContent
import emiyaj.user.model.User
import emiyaj.common.CommonStatus
import emiyaj.common.CommonRequest
import org.springframework.web.bind.annotation.*

/**
 * This is a Spring Boot Controller for the Campsite module of the application.
 * It handles HTTP requests related to the campsite content of a user.
 */
@RestController
@RequestMapping(value = ["/campsite"])
class CampsiteController {
    /**
     * This method handles GET requests at the root of the "/campsite" endpoint.
     * It retrieves the campsite content of the user specified in the request attribute.
     *
     * @param user The user whose campsite content is to be retrieved. This is passed as a request attribute.
     * @return A CampsiteResult object. If the user's campsite content is found, it is converted to a JSON string,
     * encrypted, and included in the result. If the content is not found, a bad request result is returned.
     */
    @GetMapping(value = ["/"])
    fun get(@RequestAttribute("user") user: User): CampsiteResult {
        val data = CampsiteDatabase.getContent(user) ?: return CampsiteResult.badRequest()
        return CampsiteResult.successWithEncrypt(Klaxon().toJsonString(data))
    }

    // The following code is commented out. If enabled, it would handle POST requests at the root of the "/campsite" endpoint.
    /*
    @PostMapping(value = ["/"])
    fun put(@RequestAttribute("user") user: User, @RequestBody body: CommonRequest): CampsiteResult {
        val data = Klaxon().parseArray<CampsiteContent>(body.decryptedData) ?: return CampsiteResult.badRequest()
        CampsiteDatabase.overwriteContent(user, data)
        return CampsiteResult(CommonStatus.NORMAL, "success")
    }
    */
}