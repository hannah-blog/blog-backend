package site.hannahlog.www

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class BlogBackendApplication

fun main(args: Array<String>) {
    runApplication<BlogBackendApplication>(*args)
}

@RestController
class PingPongController {

    @GetMapping("/ping")
    fun pingPong(): String = "pong!"

}
