package emiyaj.friendshippoints

import com.beust.klaxon.Klaxon
import emiyaj.user.model.User
import emiyaj.friendshippoint.AddFriendshipPointBody
import emiyaj.common.CommonRequest
import emiyaj.friendshippoint.FriendshipPointsResult
import emiyaj.util.Validation
import org.springframework.web.bind.annotation.*

/**
 * This is a Spring Boot Controller for the Friendship Points module of the application.
 * It handles HTTP requests related to the friendship points of a user.
 */
@RestController
@RequestMapping(value = ["points"])
class FriendshipPointsController {

    /**
     * This method handles GET requests at the "/points/get" endpoint.
     * It retrieves the friendship points of the user specified in the request attribute.
     *
     * @param user The user whose friendship points are to be retrieved. This is passed as a request attribute.
     * @return A FriendshipPointsResult object. If the user's friendship points are found, they are converted to a JSON string,
     * encrypted, and included in the result. If the points are not found, a bad request result is returned.
     */
    @GetMapping(value = ["get"])
    fun get(@RequestAttribute("user") user: User): FriendshipPointsResult {
        val data = FriendshipPointDatabase.getPoints(user._id)
        return FriendshipPointsResult.successWithDataAndEncrypt(Klaxon().toJsonString(data))
    }

    // The following code is commented out. If enabled, it would handle POST requests at the "/points/add" endpoint and DELETE requests at the "/points/delete/{code}" endpoint.
    /*
    @PostMapping(value = ["add"])
    fun add(@RequestBody request: CommonRequest, @RequestAttribute("user") user: User): FriendshipPointsResult {
        val data = Validation.encryptedTime(request.decryptedData) ?: return FriendshipPointsResult.badRequest()
        val json = Klaxon().parse<AddFriendshipPointBody>(data) ?: return FriendshipPointsResult.badRequest()
        FriendshipPointDatabase.addPoint(user._id, json.code, json.point)
        return FriendshipPointsResult.success()
    }

    @DeleteMapping(value = ["delete/{code}"])
    fun delete(@PathVariable code: String, @RequestAttribute("user") user: User): FriendshipPointsResult {
        if (!Validation.villagerCode(code)) return FriendshipPointsResult.badRequest()
        FriendshipPointDatabase.removeVillager(user._id, code)
        return FriendshipPointsResult.success()
    }
    */
}