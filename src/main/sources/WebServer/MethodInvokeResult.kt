package  WebServer

import DAO.DataClasses.Note

public class MethodInvokeResult private() {
    public enum class ResultCodes {
        SUCCESS {
            override val JSONRPCCode: String = "0"
        }

        USER_EXISTS_ERROR {
            override val JSONRPCCode: String = "1"
        }

        INVALID_LOGIN_ERROR {
            override val JSONRPCCode: String = "2"
        }

        INVALID_PASSWORD_ERROR {
            override val JSONRPCCode: String = "3"
        }

        USER_NOT_EXISTS_ERROR {
            override val JSONRPCCode: String = "4"
        }

        INVALID_NOTE_ID_ERROR {
            override val JSONRPCCode: String = "5"
        }

        NOTE_NOT_EXISTS_ERROR {
            override val JSONRPCCode: String = "6"
        }

        VERIFICATION_ERROR {
            override val JSONRPCCode: String = "7"
        }

        abstract val JSONRPCCode: String
    }

    public data class UserGetAllNotesReturnResult(var resultCode: String? = null,
                                                  var noteIds: Array<Long>? = null)

    public data class UserGetNotesWithIdsReturnResult(var resultCode: String? = null,
                                                      var notes: Array<Note>? = null)

    public data class UserAddNoteReturnResult(var resultCode: String? = null,
                                              var noteId: Long? = null)

    public data class UserDeleteNoteReturnResult(var resultCode: String? = null)
}