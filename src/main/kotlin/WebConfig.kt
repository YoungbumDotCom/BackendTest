package emiyaj

import emiyaj.util.token.TokenValidationInterceptor
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * 이것은 EmiyaJ 프로젝트의 설정 클래스입니다.
 * Configuration 어노테이션을 사용하여 이것이 Spring의 설정 클래스임을 나타냅니다.
 * WebMvcConfigurer 인터페이스를 구현하여 Spring MVC의 Java 기반 설정을 사용자 정의하는 콜백 메서드를 제공합니다.
 */
@Configuration
open class WebConfig : WebMvcConfigurer {

    /**
     * 이 메서드는 전체 애플리케이션에 대한 CORS 매핑을 추가하는 데 사용됩니다.
     * @param registry CORS 등록을 돕는 CorsRegistry의 인스턴스입니다.
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://127.0.0.1:9999", "http://localhost:3000", "https://youngbum.com")
    }

    /**
     * 이 메서드는 애플리케이션에 인터셉터를 추가하는 데 사용됩니다.
     * @param registry 인터셉터 등록을 돕는 InterceptorRegistry의 인스턴스입니다.
     */
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(TokenValidationInterceptor()).excludePathPatterns("/post/get")
    }

    /**
     * 이 메서드는 애플리케이션이 종료될 때 호출됩니다.
     */
    @PreDestroy
    fun closeDatabase() {
    }
}