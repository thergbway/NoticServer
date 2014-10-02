package WebServer

public trait WebServerMethodInvoker {
    fun userCreate(login: String, password: String): String
}