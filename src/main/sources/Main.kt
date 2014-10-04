import WebServer.WebServer
import WebServer.WebServerMethodInvokerImpl
import LoggingUtils.Loggers
import LoggingUtils.LoggingUtils

fun main(args: Array<String>) {
    LoggingUtils.initLoggingSystem()
    Loggers.mainCycle.info("Initializing application")

    val webServer = WebServer(WebServerMethodInvokerImpl())
    webServer.start()
    Thread.sleep(400000L)
    webServer.stop()
}