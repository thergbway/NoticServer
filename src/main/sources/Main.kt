import WebServer.WebServer
import WebServer.WebServerMethodInvoker
import LoggingUtils.Loggers
import LoggingUtils.LoggingUtils

fun main(args: Array<String>) {
    LoggingUtils.initLoggingSystem()
    Loggers.mainCycle.info("Initializing application")

    val webServer = WebServer(object : WebServerMethodInvoker {

        override fun userCreate(login: String, password: String): String {
            println("user create called")
            return "Success"
        }
    })
    webServer.start()
    Thread.sleep(600000L)
    webServer.stop()
}