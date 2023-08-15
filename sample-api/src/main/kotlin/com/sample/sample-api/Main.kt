import com.sample.client.smt.SmtApiClient
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    println("Starting server...")
    val smtApiClient = SmtApiClient()
    embeddedServer(Netty, 8080) {
        routing {
            get("/") {
                call.respondText(smtApiClient.getDemons().toString())
            }
        }
    }.start(wait = true)
}