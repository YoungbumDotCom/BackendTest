package emiyaj.util

import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * This class intercepts HTTP requests and retrieves the client's IP address.
 * It is annotated with @Component to be automatically picked up by Spring.
 */
@Component
class ClientIp : HandlerInterceptorAdapter() {
    // Map to store the IP addresses and their corresponding session times
    private val ipSession = mutableMapOf<String, Long>()

    /**
     * This function is called before the request is handled.
     * It retrieves the client's IP address and checks if the client has sent too many requests.
     *
     * @param request The HttpServletRequest object associated with the incoming request.
     * @param response The HttpServletResponse object associated with the response to the request.
     * @param handler The handler object chosen to execute the request.
     * @return True if the request should be handled, false otherwise.
     */
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.method == "OPTIONS") return true
        val ip = getIp(request) ?: return false
        return true
        /*return if (checkIp(ip)) {
            true
        } else {
            response.status = 429
            response.writer.print("Too Many Requests")
            false
        }*/
    }

    /**
     * This function checks if a client with a given IP address has sent too many requests.
     * It uses a map to store the IP addresses and their corresponding session times.
     *
     * @param ip The IP address of the client.
     * @return True if the client has not sent too many requests, false otherwise.
     */
    private fun checkIp(ip: String): Boolean {
        val now = System.currentTimeMillis() / 1000
        val session = ipSession[ip]

        return if (session == null || session + 10 <= now) {
            ipSession[ip] = now
            true
        } else {
            false
        }
    }

    companion object {
        /**
         * This function retrieves the client's IP address from a given HttpServletRequest object.
         * It checks several headers in the request for the IP address.
         * If none of the headers contain the IP address, it retrieves it from the request's remote address.
         *
         * @param request The HttpServletRequest object associated with the incoming request.
         * @return The client's IP address, or null if it cannot be retrieved.
         */
        fun getIp(request: HttpServletRequest): String? {
            var ip = request.getHeader("X-Forwarded-For")
            if (ip == null) {
                ip = request.getHeader("Proxy-Client-IP")
            }
            if (ip == null) {
                ip = request.getHeader("WL-Proxy-Client-IP")
            }
            if (ip == null) {
                ip = request.getHeader("HTTP_CLIENT_IP")
            }
            if (ip == null) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR")
            }
            if (ip == null) {
                ip = request.remoteAddr
            }
            return ip
        }
    }
}