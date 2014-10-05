package WebServer

import org.junit.Test as test
import kotlin.test.fail

class WebServerTest{
    test fun shouldStartAndStop() {
        val webServer = WebServer.WebServer(object : WebServer.WebServerMethodInvoker{

            override fun userCreate(login: String, password: String): MethodInvokeResult.ResultCodes {
                throw UnsupportedOperationException()
            }
            override fun userGetAllNoteIds(login: String, password: String): MethodInvokeResult.UserGetAllNotesReturnResult {
                throw UnsupportedOperationException()
            }
            override fun userGetNotesWithIds(login: String, password: String, noteIds: Array<Long>): MethodInvokeResult.UserGetNotesWithIdsReturnResult {
                throw UnsupportedOperationException()
            }
            override fun userAddNote(login: String, password: String, title: String, text: String): MethodInvokeResult.UserAddNoteReturnResult {
                throw UnsupportedOperationException()
            }
            override fun userDeleteNote(login: String, password: String, noteId: Long): MethodInvokeResult.UserDeleteNoteReturnResult {
                throw UnsupportedOperationException()
            }
        })
        webServer.start()
        webServer.stop()
    }
    test fun shouldThrowExceptionOnPortCallWhenServerIsStopped() {
        val webServer = WebServer.WebServer(object : WebServer.WebServerMethodInvoker{

            override fun userCreate(login: String, password: String): MethodInvokeResult.ResultCodes {
                throw UnsupportedOperationException()
            }
            override fun userGetAllNoteIds(login: String, password: String): MethodInvokeResult.UserGetAllNotesReturnResult {
                throw UnsupportedOperationException()
            }
            override fun userGetNotesWithIds(login: String, password: String, noteIds: Array<Long>): MethodInvokeResult.UserGetNotesWithIdsReturnResult {
                throw UnsupportedOperationException()
            }
            override fun userAddNote(login: String, password: String, title: String, text: String): MethodInvokeResult.UserAddNoteReturnResult {
                throw UnsupportedOperationException()
            }
            override fun userDeleteNote(login: String, password: String, noteId: Long): MethodInvokeResult.UserDeleteNoteReturnResult {
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