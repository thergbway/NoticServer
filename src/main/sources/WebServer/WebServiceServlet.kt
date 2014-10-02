package WebServer

import javax.servlet.http.HttpServlet
import javax.servlet.ServletConfig
import com.googlecode.jsonrpc4j.JsonRpcServer
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.util.logging.Level

public fun WEB_SERVICE_SERVLET_NAME(): String = "WebServiceServletName"

private class WebServiceServlet : HttpServlet() {
    private var jsonRpcServer: JsonRpcServer? = null


    override fun init(config: ServletConfig?) {
        super<HttpServlet>.init(config)

        val methodInvoker =
                getServletContext()!!.getAttribute(WEB_SERVICE_SERVLET_NAME()) as WebServerMethodInvoker
        val webService = WebService(methodInvoker)

        jsonRpcServer = JsonRpcServer(webService, javaClass<WebService>())
        jsonRpcServer!!.setAllowLessParams(true)
        jsonRpcServer!!.setAllowExtraParams(true)
        jsonRpcServer!!.setExceptionLogLevel(Level.INFO)


    }
    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        jsonRpcServer!!.handle(req, resp)
    }
}