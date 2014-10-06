package WebServer

import WebServer.MethodInvokeResult.*
import DAO.DAO

public class WebServerMethodInvokerImpl() : WebServerMethodInvoker {
    override fun userCreate(login: String, password: String): ResultCodes {
        var checkForErrorResult = MethodInvokesValidator.checkForErrorsInLoginAndPassword(login, password)
        if (checkForErrorResult != null)
            return checkForErrorResult!!

        return DAO.addNewUser(login, password)
    }

    override fun userGetAllNoteIds(login: String, password: String): UserGetAllNoteIdsReturnResult {
        var checkForErrorResult = MethodInvokesValidator.checkForErrorsInLoginAndPassword(login, password)
        if (checkForErrorResult != null)
            return UserGetAllNoteIdsReturnResult(checkForErrorResult!!.jsonRpcCode, null)

        return DAO.getAllNoteIds(login, password)
    }

    override fun userGetNotesWithIds(login: String, password: String, noteIds: Array<Long>): UserGetNotesWithIdsReturnResult {
        var checkForErrorResult = MethodInvokesValidator.checkForErrorsInLoginAndPassword(login, password)
        if (checkForErrorResult != null)
            return UserGetNotesWithIdsReturnResult(checkForErrorResult!!.jsonRpcCode, null)

        return DAO.getNotesWithIdsForUser(login, password, noteIds)
    }

    override fun userAddNote(login: String, password: String, title: String, text: String): UserAddNoteReturnResult {
        var checkForErrorResult = MethodInvokesValidator.checkForErrorsInLoginAndPassword(login, password)
        if (checkForErrorResult != null)
            return UserAddNoteReturnResult(checkForErrorResult!!.jsonRpcCode, null)

        return DAO.addNoteForUser(login, password, title, text)
    }

    override fun userDeleteNote(login: String, password: String, noteId: Long): UserDeleteNoteReturnResult {
        var checkForErrorResult = MethodInvokesValidator.checkForErrorsInLoginAndPassword(login, password)
        if (checkForErrorResult != null)
            return UserDeleteNoteReturnResult(checkForErrorResult!!.jsonRpcCode)

        return DAO.deleteNoteForUser(login, password, noteId)
    }
}