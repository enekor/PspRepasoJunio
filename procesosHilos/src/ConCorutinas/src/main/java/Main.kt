import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main() {
    coroutineScope {
        launch {
            val ping = Ping.ping("google.es")
            println(ping)
        }
        launch {
            val ping = Ping.ping("github.com")
            println(ping)
        }
        launch {
            val ping = Ping.ping("twitter.com")
            println(ping)
        }
        launch {
            val ping = Ping.ping("pinterest.es")
            println(ping)
        }
        println("Procesando todas las peticiones")
    }
}