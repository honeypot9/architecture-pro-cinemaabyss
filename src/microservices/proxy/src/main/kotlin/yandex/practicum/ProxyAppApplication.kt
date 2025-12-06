package yandex.practicum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class ProxyAppApplication
fun main(args: Array<String>) {
    runApplication<ProxyAppApplication>(*args)
}