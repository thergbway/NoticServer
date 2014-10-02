package LoggingUtils

import org.apache.log4j.xml.DOMConfigurator
import org.apache.log4j.Logger

public object LoggingUtils {
    public fun initLoggingSystem() {
        DOMConfigurator.configure(javaClass.getResource("/cfg/log4j.xml"))
    }
}

public object Loggers {
    public val root: Logger = Logger.getRootLogger() as Logger
    public val mainCycle: Logger = Logger.getLogger("MainCycle") as Logger
    public val webServer: Logger = Logger.getLogger("WebServer") as Logger
}