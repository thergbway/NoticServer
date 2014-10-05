package WebServer

import java.util.regex.Pattern
import WebServer.MethodInvokeResult.ResultCodes

private object MethodInvokesValidator {
    public fun checkForErrorsInLoginAndPassword(login: String, password: String): ResultCodes? {
        var checkForErrorResult = checkForErrorsInLogin(login)
        if (checkForErrorResult != null)
            return checkForErrorResult!!

        checkForErrorResult = checkForErrorsInPassword(password)
        if (checkForErrorResult != null)
            return checkForErrorResult!!

        return null
    }

    public fun checkForErrorsInLogin(login: String): ResultCodes? {
        return if (!containsOnlyDigitsAndLettersAnd_(login)) ResultCodes.INVALID_LOGIN_ERROR
        else null
    }

    public fun checkForErrorsInPassword(password: String): ResultCodes? {
        return if (!containsOnlyDigitsAndLettersAnd_(password)) ResultCodes.INVALID_PASSWORD_ERROR
        else null
    }

    private fun containsOnlyDigitsAndLettersAnd_(strToCheck: String): Boolean {
        return if (Pattern.matches("\\w+", strToCheck)) true else false
    }
}