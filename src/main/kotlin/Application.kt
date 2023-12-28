package emiyaj

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration

/**
 * 이것은 EmiyaJ 프로젝트의 주요 애플리케이션 클래스입니다.
 * SpringBootApplication 어노테이션을 사용하여 단일 Spring Boot 애플리케이션임을 나타냅니다.
 * SpringBootApplication 어노테이션에서 MongoAutoConfiguration과 MongoDataAutoConfiguration을 제외하여
 * MongoDB에 대한 자동 구성을 비활성화합니다.
 */
@SpringBootApplication(exclude = [MongoAutoConfiguration::class, MongoDataAutoConfiguration::class])
open class Application

/**
 * 이것은 애플리케이션이 시작될 때 실행될 주요 함수입니다.
 * SpringApplication의 run 메서드를 사용하여 Spring 애플리케이션을 시작합니다.
 * @param args 애플리케이션에 명령 줄 인수를 전달하기 위한 문자열 배열입니다.
 */
fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}