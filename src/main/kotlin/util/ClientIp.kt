package emiyaj.util

import org.springframework.stereotype.Component
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ClientIp : HandlerInterceptorAdapter() {
    private val ipSession = mutableMapOf<String, Long>()

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