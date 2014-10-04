package WebServer

import WebServer.MethodInvokeResultCodes.UserCreateMethodReturnCodes

public trait WebServerMethodInvoker {
    fun userCreate(login: String, password: String): UserCreateMethodReturnCodes
}