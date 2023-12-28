package emiyaj.common

import emiyaj.user.UserDatabase
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import jakarta.servlet.http.HttpServletRequest

/**
 * 이 클래스는 루트("/") 및 오류("/error") 경로에 대한 HTTP 요청을 처리합니다.
 * @RestController 어노테이션이 붙어 있어, 이것이 응답 본문에 직접 데이터를 반환하는 Spring MVC 컨트롤러임을 나타냅니다.
 * 또한 클래스 수준에서 @RequestMapping 어노테이션이 붙어 있어, 이 클래스의 모든 메서드가 "/" 경로에 대한 요청을 처리함을 나타냅니다.
 * 오류를 처리하기 위해 ErrorController 인터페이스를 구현합니다.
 */
@RestController
@RequestMapping(value = ["/"])
class IndexController : ErrorController {
    // 오류 페이지로의 경로
    private val path = "/error"

    /**
     * 이 함수는 "/error" 경로에 대한 요청을 처리합니다.
     * INTERNAL_ERROR 상태와 "Internal Server Error."라는 코멘트를 가진 CommonResult를 반환합니다.
     *
     * @param req 들어오는 요청과 연관된 HttpServletRequest 객체입니다.
     * @return INTERNAL_ERROR 상태와 "Internal Server Error."라는 코멘트를 가진 CommonResult 객체를 반환합니다.
     */
    @RequestMapping(value = ["/error"])
    fun error(req: HttpServletRequest): CommonResult {
        //println(req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
        return CommonResult(status = CommonStatus.INTERNAL_ERROR, comment = "Internal Server Error.")
    }

    /**
     * 이 함수는 "/" 경로에 대한 요청을 처리합니다.
     * NORMAL 상태와 "normal"이라는 코멘트를 가진 CommonResult를 반환합니다.
     *
     * @return NORMAL 상태와 "normal"이라는 코멘트를 가진 CommonResult 객체를 반환합니다.
     */
    @RequestMapping(value = ["/"])
    fun index(): CommonResult {
        return CommonResult(status = CommonStatus.NORMAL, comment = "normal")
    }

    /**
     * 이 함수는 모든 사용자의 주민들을 기본 그룹을 가진 맵으로 마이그레이션합니다.
     * 현재는 주석 처리되어 있습니다.
     *
     * @return 모든 사용자의 목록을 반환합니다.
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