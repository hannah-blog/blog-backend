package stie.hannahlog.blogBackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BlogBackendApplication

fun main(args: Array<String>) {
    runApplication<BlogBackendApplication>(*args)
}
