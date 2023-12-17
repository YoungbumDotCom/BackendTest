package emiyaj.common

import emiyaj.user.UserDatabase
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
 * This class handles HTTP requests to the root ("/") and error ("/error") paths.
 * It is annotated with @RestController, indicating that it is a Spring MVC controller that returns data directly in the response body.
 * It is also annotated with @RequestMapping at the class level, specifying that all methods in this class handle requests to the "/" path.
 * It implements the ErrorController interface to handle errors.
 */
@RestController
@RequestMapping(value = ["/"])
class IndexController : ErrorController {
    // The path to the error page
    private val path = "/error"

    /**
     * This function handles requests to the "/error" path.
     * It returns a CommonResult with an INTERNAL_ERROR status and a comment of "Internal Server Error."
     *
     * @param req The HttpServletRequest object associated with the incoming request.
     * @return A CommonResult object with an INTERNAL_ERROR status and a comment of "Internal Server Error."
     */
    @RequestMapping(value = ["/error"])
    fun error(req: HttpServletRequest): CommonResult {
        //println(req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
        return CommonResult(status = CommonStatus.INTERNAL_ERROR, comment = "Internal Server Error.")
    }

    /**
     * This function handles requests to the "/" path.
     * It returns a CommonResult with a NORMAL status and a comment of "normal".
     *
     * @return A CommonResult object with a NORMAL status and a comment of "normal".
     */
    @RequestMapping(value = ["/"])
    fun index(): CommonResult {
        return CommonResult(status = CommonStatus.NORMAL, comment = "normal")
    }

    /**
     * This function migrates all users' villagers from a list to a map with a default group.
     * It is currently commented out.
     *
     * @return A list of all users.
     */
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