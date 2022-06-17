package emiyaj.common

import emiyaj.user.UserDatabase
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping(value = ["/"])
class IndexController : ErrorController {
    private val path = "/error"

    @RequestMapping(value = ["/error"])
    fun error(req: HttpServletRequest): CommonResult {
        //println(req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
        return CommonResult(status = CommonStatus.INTERNAL_ERROR, comment = "Internal Server Error.")
    }

    @RequestMapping(value = ["/"])
    fun index(): CommonResult {
        return CommonResult(status = CommonStatus.NORMAL, comment = "normal")
    }

    /*@RequestMapping(value = ["/migrate"])
    fun migrate(): List<Any> {
        val users = UserDatabase.getAllUsers()
        for (user in users) {
            UserDatabase.migrateMyVillagers(user.username)
            println(user.username)
        }
        return users
    }*/

    //fun getErrorPath(): String = path
}