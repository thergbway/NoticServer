package WebServer

import org.junit.Test as test
import kotlin.test.assertEquals
import ServerConfiguration.ServerConfiguration
import com.googlecode.jsonrpc4j.JsonRpcHttpClient
import java.net.URL
import WebServer.MethodInvokeResult.*
import org.junit.Before
import org.junit.After
import DAO.DataClasses.Note
import kotlin.test.fail
import kotlin.test.assertTrue

public class JsonRpcMethodsTest {
    var webServer: WebServer? = null
    var client: JsonRpcHttpClient? = null

    Before fun beforeClass() {
        webServer = WebServer(WebServerMethodInvokerImpl())
        webServer!!.start()
        val port = ServerConfiguration.getProperty("port") as Int
        client = JsonRpcHttpClient(URL("http://127.0.0.1:$port/jsonrpc.api"))
    }

    After fun afterClass() {
        webServer!!.stop()
    }

    test fun userCreateMethodTest() {
        userCreateInvoke("w45hrtst", "w45yfg")
        assertEquals(userCreateInvoke("w45hrtst", "w45yfg"), ResultCodes.USER_EXISTS_ERROR.jsonRpcCode)
    }

    test fun userGetNotesWithIdsTest() {
        val login = "testLogin1"
        val pass = "testPassword1"
        userCreateInvoke(login, pass)
        assertEquals(userAddNote(login, "wrongPassword", "fths", "s5h4w").resultCode,
                ResultCodes.VERIFICATION_ERROR.jsonRpcCode)
        val title1 = "title1"
        val title2 = "title2"
        val text1 = "text1"
        val text2 = "text2"
        val noteId1 = userAddNote(login, pass, title1, text1).noteId!!
        val noteId2 = userAddNote(login, pass, title2, text2).noteId!!
        val notes = userGetNotesWithIds(login, pass, array(noteId1, noteId2)).notes!!
        notes.forEach {
            if (it != Note(noteId1, login, title1, text1) && it != Note(noteId2, login, title2, text2))
                fail("Unexpected note")
        }
    }

    test fun userGetAllNoteIdsTest() {
        val login = "s45ysv"
        val password = "sge432"
        userCreateInvoke(login, password)
        assertEquals(userGetAllNoteIds(login, "wrongPassword").resultCode, ResultCodes.VERIFICATION_ERROR.jsonRpcCode)
        val title1 = "title1"
        val title2 = "title2"
        val text1 = "text1"
        val text2 = "text2"
        val noteId1 = userAddNote(login, password, title1, text1).noteId!!
        val noteId2 = userAddNote(login, password, title2, text2).noteId!!
        val ids = userGetAllNoteIds(login, password).noteIds!!
        assertTrue(ids.contains(noteId1) && ids.contains(noteId2))
    }

    test fun userAddNoteTest() {
        val login = "s45ys4ghv"
        val password = "sg45ye432"
        userCreateInvoke(login, password)
        assertEquals(userAddNote(login, "wrongPassword", "she6", "whhbdf").resultCode,
                ResultCodes.VERIFICATION_ERROR.jsonRpcCode)
        assertEquals(userAddNote(login, password, "title1", "ggae4").resultCode, ResultCodes.SUCCESS.jsonRpcCode)
    }

    test fun userDeleteNoteTest() {
        val login = "we45hgf"
        val password = "shh5fgb"
        userCreateInvoke(login, password)
        assertEquals(userDeleteNote(login, "wrongPassword", 34612).resultCode,
                ResultCodes.VERIFICATION_ERROR.jsonRpcCode)
        val noteId = userAddNote(login, password, "4ttdf", "w4ygfnjd").noteId!!
        assertEquals(userDeleteNote(login, password, noteId).resultCode, ResultCodes.SUCCESS.jsonRpcCode)
    }

    private fun userDeleteNote(login: String, password: String, noteId: Long): UserDeleteNoteReturnResult {
        return jsonRpcMethodInvoke("user.deleteNote", mapOf("login" to login, "password" to password,
                "noteId" to noteId),
                javaClass<UserDeleteNoteReturnResult>()) as UserDeleteNoteReturnResult
    }

    private fun userCreateInvoke(login: String, password: String): String {
        return jsonRpcMethodInvoke("user.create", mapOf("login" to login, "password" to password), javaClass<String>())
                as String
    }

    private fun userAddNote(login: String, password: String, title: String, text: String): UserAddNoteReturnResult {
        return jsonRpcMethodInvoke("user.addNote", mapOf("login" to login, "password" to password, "title" to title,
                "text" to text), javaClass<UserAddNoteReturnResult>()) as UserAddNoteReturnResult
    }

    private fun userGetNotesWithIds(login: String, password: String, noteIds: Array<Long>)
            : UserGetNotesWithIdsReturnResult {
        return jsonRpcMethodInvoke("user.getNotesWithIds", mapOf("login" to login, "password" to password,
                "noteIds" to noteIds), javaClass<UserGetNotesWithIdsReturnResult>()) as UserGetNotesWithIdsReturnResult
    }

    private fun userGetAllNoteIds(login: String, password: String): UserGetAllNoteIdsReturnResult {
        return jsonRpcMethodInvoke("user.getAllNoteIds", mapOf("login" to login, "password" to password),
                javaClass<UserGetAllNoteIdsReturnResult>()) as UserGetAllNoteIdsReturnResult
    }

    private fun jsonRpcMethodInvoke(method: String, params: Map<String, Any>, returnClass: Class<out Any>): Any {
        return client!!.invoke(method, params, returnClass)!!
    }
}