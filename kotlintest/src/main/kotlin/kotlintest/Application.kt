package kotlindemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate


data class Language(var language: String = "")
data class Request(var url: String = "")

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    System.getProperties().put( "server.port", 8010)
    SpringApplication.run(Application::class.java, *args)
}

@RestController
class GreetingController {
    @GetMapping("/language")
    fun getLanguage() = Language("Kotlin")

    @PostMapping("/request")
    fun postRequest(@RequestBody request: Request): Language {
        val restTemplate = RestTemplate()
        val language = restTemplate.getForObject(request.url, Language::class.java)

        //return Language(language.language)
        return language
    }
}