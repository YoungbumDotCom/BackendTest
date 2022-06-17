package emiyaj.util

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.springframework.core.MethodParameter
import org.springframework.http.HttpInputMessage
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice
import java.lang.reflect.Type
import java.util.*
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ReportAllRequest(val request: HttpServletRequest) : RequestBodyAdvice {
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

    override fun beforeBodyRead(
        inputMessage: HttpInputMessage,
        parameter: MethodParameter,
        targetType: Type,
        converterType: Class<out HttpMessageConverter<*>>
    ): HttpInputMessage {
        return inputMessage
    }

    override fun handleEmptyBody(
        body: Any?,
        inputMessage: HttpInputMessage,
        parameter: MethodParameter,
        targetType: Type,
        converterType: Class<out HttpMessageConverter<*>>
    ): Any? {
        return body
    }

    override fun supports(
        methodParameter: MethodParameter,
        targetType: Type,
        converterType: Class<out HttpMessageConverter<*>>
    ): Boolean {
        return true
    }
}