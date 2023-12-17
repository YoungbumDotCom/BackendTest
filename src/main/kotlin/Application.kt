package emiyaj

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration

/**
 * This is the main application class for the EmiyaJ project.
 * It uses the SpringBootApplication annotation to indicate that it's a single Spring Boot application.
 * The MongoAutoConfiguration and MongoDataAutoConfiguration are excluded from the SpringBootApplication annotation
 * to disable the auto-configuration for MongoDB.
 */
@SpringBootApplication(exclude = [MongoAutoConfiguration::class, MongoDataAutoConfiguration::class])
open class Application

/**
 * This is the main function which will be executed when the application starts.
 * It uses the SpringApplication's run method to launch the Spring application.
 * @param args Array of strings to pass command line arguments to the application.
 */
fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}