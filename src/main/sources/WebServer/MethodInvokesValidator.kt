package WebServer

import java.util.regex.Pattern

private object MethodInvokesValidator {
    public fun checkForErrorsInUserCreateMethodParams(login: String, password: String)
            : MethodInvokeResultCodes.UserCreateMethodReturnCodes? {
        if (!Pattern.matches("\\w+", login))
            return MethodInvokeResultCodes.UserCreateMethodReturnCodes.INVALID_LOGIN_ERROR
        if (!Pattern.matches("\\w+", password))
            return MethodInvokeResultCodes.UserCreateMethodReturnCodes.INVALID_PASSWORD_ERROR
        return null;
    }
}