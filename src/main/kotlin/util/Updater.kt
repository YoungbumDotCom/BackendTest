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

@Component
object Updater {
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

    private fun shutdown() {
        println("Error! Shutting down server...")
        System.exit(1)
    }
}