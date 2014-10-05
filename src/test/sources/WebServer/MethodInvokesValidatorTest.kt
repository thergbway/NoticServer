package WebServer

import org.junit.Test as test
import kotlin.test.assertEquals

import WebServer.MethodInvokeResult.ResultCodes

class MethodInvokesValidatorTest {
    test fun shouldValidateLogin() {
        assertEquals(MethodInvokesValidator.checkForErrorsInLoginAndPassword("valid_login_14235f", "password"),
                null)
        assertEquals(MethodInvokesValidator.checkForErrorsInLoginAndPassword("@invalid_login_14235f", "password"),
                ResultCodes.INVALID_LOGIN_ERROR)
        assertEquals(MethodInvokesValidator.checkForErrorsInLoginAndPassword("invalid_login_14235    f", "password"),
                ResultCodes.INVALID_LOGIN_ERROR)
    }

    test fun shouldValidatePassword() {
        assertEquals(MethodInvokesValidator.checkForErrorsInLoginAndPassword("valid_login_12f", "valid_pass_123"),
                null)
        assertEquals(MethodInvokesValidator.checkForErrorsInLoginAndPassword("valid_login_12f", "@invalid_pass_123"),
                ResultCodes.INVALID_PASSWORD_ERROR)
        assertEquals(MethodInvokesValidator.checkForErrorsInLoginAndPassword("valid_login_12f", "invalid!"),
                ResultCodes.INVALID_PASSWORD_ERROR)
    }
}