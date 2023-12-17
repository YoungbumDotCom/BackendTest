package emiyaj

import emiyaj.database.MongoDB
import emiyaj.util.ClientIp
import emiyaj.util.token.TokenValidationInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.annotation.PreDestroy

/**
 * This is the configuration class for the EmiyaJ project.
 * It uses the Configuration annotation to indicate that it's a configuration class for Spring.
 * It implements the WebMvcConfigurer interface to provide callback methods to customize the Java-based configuration for Spring MVC.
 */
@Configuration
open class WebConfig : WebMvcConfigurer {

    /**
     * This method is used to add CORS mappings for the entire application.
     * @param registry An instance of CorsRegistry which assists with the registration of CORS.
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedOrigins("http://127.0.0.1:9999", "http://127.0.0.1:3000", "https://emiya.vxz.me", "https://dodo.ij.rs")
    }

    /**
     * This method is used to add interceptors for the application.
     * @param registry An instance of InterceptorRegistry which assists with the registration of Interceptors.
     */
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(ClientIp())
        registry.addInterceptor(TokenValidationInterceptor()).addPathPatterns("/points/**", "/campsite/**")
    }

    /**
     * This method is called when the application is closing.
     * It's used to close the MongoDB database connection.
     */
    @PreDestroy
    fun closeDatabase() {
        MongoDB.closeDatabase()
        println("MongoDB closed.")
    }
}