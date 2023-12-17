package emiyaj.util

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import emiyaj.Application
import emiyaj.clothes.ClothesDatabase
import emiyaj.clothes.model.Clothes
import emiyaj.clothes.model.ClothesJson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.boot.SpringApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.net.URL
import javax.annotation.PostConstruct

/**
 * This object represents an updater for the EmiyaJ application.
 * It provides methods to update the clothes data from a remote JSON source.
 */
@Component
object Updater {
    /**
     * This method is called after the Spring application context is initialized.
     * It checks if the application is running in debug mode and updates the clothes data if not.
     *
     * Example usage:
     * ```kotlin
     * Updater.update()
     * ```
     */
    @PostConstruct
    fun update() {
        val isDebug = EnvVariable.getVariable("DEBUG")
        if (isDebug?.lowercase() == "true") {
            println("DEBUG mode is enabled. Passing clothes data update.")
        } else {
            val parser = Json { ignoreUnknownKeys = true; isLenient = true }
            println("Updating Clothes data...")

            val topsJson =
                URL("https://raw.githubusercontent.com/Norviah/animal-crossing/master/json/data/Tops.json").readText()
            val bottomsJson =
                URL("https://raw.githubusercontent.com/Norviah/animal-crossing/master/json/data/Bottoms.json").readText()
            val dressJson =
                URL("https://raw.githubusercontent.com/Norviah/animal-crossing/master/json/data/Dress-Up.json").readText()
            val tops: List<ClothesJson> = parser.decodeFromString(topsJson) ?: listOf()
            val bottoms: List<ClothesJson> = parser.decodeFromString(bottomsJson) ?: listOf()
            val dress: List<ClothesJson> = parser.decodeFromString(dressJson) ?: listOf()
            ClothesDatabase.removeData()
            ClothesDatabase.updateData(tops)
            ClothesDatabase.updateData(bottoms)
            ClothesDatabase.updateData(dress)
            println("Clothes data updated.")
        }
    }

    /**
     * This method is used to shut down the server.
     * It prints an error message and exits the application with a status code of 1.
     *
     * Example usage:
     * ```kotlin
     * Updater.shutdown()
     * ```
     */
    private fun shutdown() {
        println("Error! Shutting down server...")
        System.exit(1)
    }
}