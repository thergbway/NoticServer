package WebServer

public class MethodInvokeResultCodes private() {
    public enum class UserCreateMethodReturnCodes {
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

        abstract val JSONRPCCode: String
    }
}