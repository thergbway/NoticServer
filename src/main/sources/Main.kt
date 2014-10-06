import WebServer.WebServer
import WebServer.WebServerMethodInvokerImpl
import LoggingUtils.Loggers
import LoggingUtils.LoggingUtils

fun main(args: Array<String>) {
    LoggingUtils.initLoggingSystem()
    Loggers.mainCycle.info("Initializing application")
    Loggers.mainCycle.info("Press Ctrl+C to stop the server")

    val webServer = WebServer(WebServerMethodInvokerImpl())
    webServer.start()

    Runtime.getRuntime().addShutdownHook(Thread({
        webServer.stop()
    }))
}