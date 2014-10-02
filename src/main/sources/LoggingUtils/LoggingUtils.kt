package LoggingUtils

import org.apache.log4j.xml.DOMConfigurator
import org.apache.log4j.Logger

public object LoggingUtils {
    {
        println("yes I was called")
    }

    public fun initLoggingSystem() {
        DOMConfigurator.configure(javaClass.getResource("/cfg/log4j.xml"))
    }
}

public object Loggers {
    public val root: Logger = Logger.getRootLogger() as Logger
}