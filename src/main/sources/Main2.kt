package doo

import com.googlecode.jsonrpc4j.JsonRpcHttpClient
import java.net.URL

fun main(args: Array<String>) {
    val client = JsonRpcHttpClient(URL("http://127.0.0.1:47545/jsonrpc.api"))
    val s = client.invoke("user.create", mapOf("login" to "log23", "password" to "pasfaweg"), javaClass<String>()) as String
    println(s)
}