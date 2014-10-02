package WebServer

import org.junit.Test as test
import kotlin.test.fail

class WebServerTest{
    test fun shouldStartAndStop() {
        val webServer = WebServer.WebServer(object : WebServer.WebServerMethodInvoker{
            override fun userCreate(login: String, password: String): String {
                throw UnsupportedOperationException()
            }
        })
        webServer.start()
        webServer.stop()
    }
    test fun shouldThrowExceptionOnPortCallWhenServerIsStopped() {
        val webServer = WebServer.WebServer(object : WebServer.WebServerMethodInvoker{
            override fun userCreate(login: String, password: String): String {
                throw UnsupportedOperationException()
            }
        })
        webServer.start()
        webServer.stop()
        try{
            webServer.port
        }
        catch (e: Exception){
            return
        }
        fail("Exception was not thrown")
    }
}