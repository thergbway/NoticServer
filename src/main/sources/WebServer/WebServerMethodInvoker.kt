package WebServer

import WebServer.MethodInvokeResult.*

public trait WebServerMethodInvoker {
    fun userCreate(login: String, password: String): ResultCodes
    fun userGetAllNoteIds(login: String, password: String): UserGetAllNoteIdsReturnResult
    fun userGetNotesWithIds(login: String, password: String, noteIds: Array<Long>): UserGetNotesWithIdsReturnResult
    fun userAddNote(login: String, password: String, title: String, text: String): UserAddNoteReturnResult
    fun userDeleteNote(login: String, password: String, noteId: Long): UserDeleteNoteReturnResult
}