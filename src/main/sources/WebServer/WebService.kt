package WebServer

import com.googlecode.jsonrpc4j.JsonRpcMethod as rpcMethod
import com.googlecode.jsonrpc4j.JsonRpcParam as rpcParam
import LoggingUtils.Loggers
import WebServer.MethodInvokeResult.UserGetAllNotesReturnResult
import WebServer.MethodInvokeResult.UserGetNotesWithIdsReturnResult
import WebServer.MethodInvokeResult.UserAddNoteReturnResult
import WebServer.MethodInvokeResult.UserDeleteNoteReturnResult

private class WebService(methodInvoker: WebServerMethodInvoker) {
    private val methodInvoker: WebServerMethodInvoker = methodInvoker

    rpcMethod("user.create")
    public fun userCreate(rpcParam("login") login: String,
                          rpcParam("password") password: String): String {
        Loggers.webServer.info("Method user.create with login=$login invoked")
        return methodInvoker.userCreate(login, password).JSONRPCCode
    }

    rpcMethod("user.getAllNoteIds")
    public fun userGetAllNotes(rpcParam("login") login: String,
                               rpcParam("password") password: String): UserGetAllNotesReturnResult {
        Loggers.webServer.info("Method user.getAllNoteIds with login=$login invoked")
        return methodInvoker.userGetAllNoteIds(login, password)
    }

    rpcMethod("user.getNotesWithIds")
    public fun userGetNotesWithIds(rpcParam("login") login: String,
                                   rpcParam("password") password: String,
                                   rpcParam("noteIds") noteIds: Array<Long>): UserGetNotesWithIdsReturnResult {
        Loggers.webServer.info("Method user.getNotesWithIds with login=$login and noteIds=$noteIds invoked")
        return methodInvoker.userGetNotesWithIds(login, password, noteIds)
    }

    rpcMethod("user.addNote")
    public fun userAddNote(rpcParam("login") login: String,
                           rpcParam("password") password: String,
                           rpcParam("title") title: String,
                           rpcParam("text") text: String): UserAddNoteReturnResult {
        Loggers.webServer.info("Method user.addNote with login=$login, title=$title, text=$text invoked")
        return methodInvoker.userAddNote(login, password, title, text)
    }

    rpcMethod("user.deleteNote")
    public fun userDeleteNote(rpcParam("login") login: String,
                              rpcParam("password") password: String,
                              rpcParam("noteId") noteId: Long): UserDeleteNoteReturnResult {
        Loggers.webServer.info("Method user.deleteNote with login=$login and noteId=$noteId invoked")
        return methodInvoker.userDeleteNote(login, password, noteId)
    }
}