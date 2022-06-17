package emiyaj

import emiyaj.util.EnvVariable
import emiyaj.util.Updater
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.context.event.ApplicationPreparedEvent
import org.springframework.context.event.EventListener

@SpringBootApplication(exclude = [MongoAutoConfiguration::class, MongoDataAutoConfiguration::class])
open class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

