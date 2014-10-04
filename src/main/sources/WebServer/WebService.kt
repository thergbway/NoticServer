package WebServer

import com.googlecode.jsonrpc4j.JsonRpcMethod as rpcMethod
import com.googlecode.jsonrpc4j.JsonRpcParam as rpcParam
import LoggingUtils.Loggers

private class WebService(methodInvoker: WebServerMethodInvoker) {
    private val methodInvoker: WebServerMethodInvoker = methodInvoker

    rpcMethod("user.create")
    public fun userCreate(rpcParam("login") login: String,
                          rpcParam("password") password: String): String {
        Loggers.webServer.info("Method user.create with login=$login invoked")
        return methodInvoker.userCreate(login, password).JSONRPCCode
    }
}