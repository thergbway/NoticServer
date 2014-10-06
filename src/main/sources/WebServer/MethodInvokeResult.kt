package  WebServer

import DAO.DataClasses.Note

public class MethodInvokeResult private() {
    public enum class ResultCodes {
        SUCCESS {
            override val jsonRpcCode: String = "0"
        }

        USER_EXISTS_ERROR {
            override val jsonRpcCode: String = "1"
        }

        INVALID_LOGIN_ERROR {
            override val jsonRpcCode: String = "2"
        }

        INVALID_PASSWORD_ERROR {
            override val jsonRpcCode: String = "3"
        }

        USER_NOT_EXISTS_ERROR {
            override val jsonRpcCode: String = "4"
        }

        INVALID_NOTE_ID_ERROR {
            override val jsonRpcCode: String = "5"
        }

        NOTE_NOT_EXISTS_ERROR {
            override val jsonRpcCode: String = "6"
        }

        VERIFICATION_ERROR {
            override val jsonRpcCode: String = "7"
        }

        abstract val jsonRpcCode: String
    }

    public data class UserGetAllNoteIdsReturnResult(var resultCode: String? = null,
                                                    var noteIds: Array<Long>? = null)

    public data class UserGetNotesWithIdsReturnResult(var resultCode: String? = null,
                                                      var notes: Array<Note>? = null)

    public data class UserAddNoteReturnResult(var resultCode: String? = null,
                                              var noteId: Long? = null)

    public data class UserDeleteNoteReturnResult(var resultCode: String? = null)
}