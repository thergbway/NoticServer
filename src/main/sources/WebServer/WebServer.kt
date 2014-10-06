package WebServer

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.util.component.AbstractLifeCycle
import org.eclipse.jetty.servlet.ServletHandler

import ServerConfiguration.ServerConfiguration
import LoggingUtils.Loggers

public class WebServer(methodInvoker: WebServerMethodInvoker) {
    private val methodInvoker: WebServerMethodInvoker = methodInvoker
    private val server: Server = Server(ServerConfiguration.getProperty("port") as Int)
    public val port: Int
        get(){
            if (!server.isStarted())
                throw IllegalStateException("Server is not started. Current state is ${server.getState()}")
            return server.getURI()!!.getPort()
        }

    public fun start() {
        if (server.getState() != AbstractLifeCycle.STOPPED)
            throw IllegalStateException("Server state is not stopped! It`s current state is ${server.getState()}")
        val handler = ServletHandler()
        val entry = ServerConfiguration.getProperty("entry")
        handler.addServletWithMapping(javaClass<WebServiceServlet>(), entry as String)
        server.setHandler(handler)
        server.start()

        handler.getServletContext()!!.setAttribute(WEB_SERVICE_SERVLET_NAME(), methodInvoker)

        Loggers.webServer.info("Web server started at port $port")
        Loggers.webServer.info("JsonRpc Api is opened under the name $entry")
    }

    public fun stop() {
        server.stop()
        server.join()

        Loggers.webServer.info("Web server stopped")
    }
}