package emiyaj

import emiyaj.database.MongoDB
import emiyaj.util.ClientIp
import emiyaj.util.token.TokenValidationInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.annotation.PreDestroy

@Configuration
open class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedOrigins("http://127.0.0.1:9999", "http://127.0.0.1:3000", "https://emiya.vxz.me", "https://dodo.ij.rs")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(ClientIp())
        registry.addInterceptor(TokenValidationInterceptor()).addPathPatterns("/points/**", "/campsite/**")
    }

    @PreDestroy
    fun closeDatabase() {
        MongoDB.closeDatabase()
        println("MongoDB closed.")
    }
}