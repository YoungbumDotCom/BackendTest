package emiyaj.util

import com.beust.klaxon.Klaxon
import org.springframework.core.MethodParameter
import org.springframework.http.HttpInputMessage
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice
import java.lang.reflect.Type
import javax.servlet.http.HttpServletRequest

/**
 * This class is responsible for logging all incoming requests.
 * It is annotated with @ControllerAdvice to be automatically picked up by Spring.
 *
 * @property request The HttpServletRequest object associated with the incoming request.
 */
@ControllerAdvice
class ReportAllRequest(val request: HttpServletRequest) : RequestBodyAdvice {

    /**
     * This function is called after the request body is read.
     * It logs the request details and returns the original body.
     *
     * @param body The body of the request.
     * @param inputMessage The HttpInputMessage object associated with the request.
     * @param parameter The MethodParameter object associated with the request.
     * @param targetType The target type of the request.
     * @param converterType The HttpMessageConverter class used for the request.
     * @return The original body of the request.
     */
    override fun afterBodyRead(
        body: Any,
        inputMessage: HttpInputMessage,
        parameter: MethodParameter,
        targetType: Type,
        converterType: Class<out HttpMessageConverter<*>>
    ): Any {
        val ip = ClientIp.getIp(request) ?: "unknown"
        val jsonString = Klaxon().toJsonString(body)
        /*sendLog(
            ip = ip,
            body = jsonString,
            userAgent = request.getHeader("User-Agent"),
            path = request.servletPath,
            method = request.method
        )*/
        return body
    }

    /**
     * This function is called before the request body is read.
     * It returns the original HttpInputMessage object.
     *
     * @param inputMessage The HttpInputMessage object associated with the request.
     * @param parameter The MethodParameter object associated with the request.
     * @param targetType The target type of the request.
     * @param converterType The HttpMessageConverter class used for the request.
     * @return The original HttpInputMessage object.
     */
    override fun beforeBodyRead(
        inputMessage: HttpInputMessage,
        parameter: MethodParameter,
        targetType: Type,
        converterType: Class<out HttpMessageConverter<*>>
    ): HttpInputMessage {
        return inputMessage
    }

    /**
     * This function is called when the request body is empty.
     * It returns the original body of the request.
     *
     * @param body The body of the request.
     * @param inputMessage The HttpInputMessage object associated with the request.
     * @param parameter The MethodParameter object associated with the request.
     * @param targetType The target type of the request.
     * @param converterType The HttpMessageConverter class used for the request.
     * @return The original body of the request.
     */
    override fun handleEmptyBody(
        body: Any?,
        inputMessage: HttpInputMessage,
        parameter: MethodParameter,
        targetType: Type,
        converterType: Class<out HttpMessageConverter<*>>
    ): Any? {
        return body
    }

    /**
     * This function determines whether this advice is applicable.
     * It always returns true in this implementation.
     *
     * @param methodParameter The MethodParameter object associated with the request.
     * @param targetType The target type of the request.
     * @param converterType The HttpMessageConverter class used for the request.
     * @return True, indicating that this advice is applicable to all requests.
     */
    override fun supports(
        methodParameter: MethodParameter,
        targetType: Type,
        converterType: Class<out HttpMessageConverter<*>>
    ): Boolean {
        return true
    }
}