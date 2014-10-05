package doo

import com.googlecode.jsonrpc4j.JsonRpcHttpClient
import java.net.URL
import WebServer.MethodInvokeResult.UserGetAllNotesReturnResult
import WebServer.MethodInvokeResult.UserGetNotesWithIdsReturnResult

fun main(args: Array<String>) {
    val client = JsonRpcHttpClient(URL("http://127.0.0.1:47545/jsonrpc.api"))
    val s1 = client.invoke("user.create", mapOf("login" to "log23", "password" to "pasfaweg"), javaClass<String>()) as String
    println(s1)
    val s2 = client.invoke("user.getAllNoteIds", mapOf("login" to "testLogin", "password" to "testPassword"), javaClass<UserGetAllNotesReturnResult>()) as UserGetAllNotesReturnResult
    println(s2)
    val s3 = client.invoke("user.getNotesWithIds", mapOf("login" to "testLogin", "password" to "testPassword", "noteIds" to array(1L)), javaClass<UserGetNotesWithIdsReturnResult>()) as UserGetNotesWithIdsReturnResult
    println(s3)
}