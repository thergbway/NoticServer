import ServerConfiguration.ServerConfiguration
import LoggingUtils.*

fun main(args: Array<String>) {
    val prop1 = ServerConfiguration.getProperty("dropDBOnStart")
    val prop2 = ServerConfiguration.getProperty("port")
    val prop3 = ServerConfiguration.getProperty("entry")
    println(prop1)
    println(prop2)
    println(prop3)

    LoggingUtils.initLoggingSystem()

    Loggers.root.error("Error example")
    Loggers.root.debug("Debug example")
    Loggers.root.info("Info example")
    Loggers.root.warn("Warn message")

}