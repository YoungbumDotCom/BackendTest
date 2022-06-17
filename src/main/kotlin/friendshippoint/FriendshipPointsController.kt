package emiyaj.friendshippoints

import com.beust.klaxon.Klaxon
import emiyaj.user.model.User
import emiyaj.friendshippoint.AddFriendshipPointBody
import emiyaj.common.CommonRequest
import emiyaj.friendshippoint.FriendshipPointsResult
import emiyaj.util.Validation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["points"])
class FriendshipPointsController {

    @GetMapping(value = ["get"])
    fun get(@RequestAttribute("user") user: User): FriendshipPointsResult {
        val data = FriendshipPointDatabase.getPoints(user._id)
        return FriendshipPointsResult.successWithDataAndEncrypt(Klaxon().toJsonString(data))
    }
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