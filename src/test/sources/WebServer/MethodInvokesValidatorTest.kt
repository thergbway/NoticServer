package WebServer

import org.junit.Test as test
import kotlin.test.assertEquals

import WebServer.MethodInvokeResultCodes.UserCreateMethodReturnCodes

class MethodInvokesValidatorTest {
    test fun shouldValidateLogin() {
        assertEquals(MethodInvokesValidator.checkForErrorsInUserCreateMethodParams("valid_login_14235f", "password"),
                null)
        assertEquals(MethodInvokesValidator.checkForErrorsInUserCreateMethodParams("@invalid_login_14235f", "password"),
                UserCreateMethodReturnCodes.INVALID_LOGIN_ERROR)
        assertEquals(MethodInvokesValidator.checkForErrorsInUserCreateMethodParams("invalid_login_14235    f", "password"),
                UserCreateMethodReturnCodes.INVALID_LOGIN_ERROR)
    }

    test fun shouldValidatePassword() {
        assertEquals(MethodInvokesValidator.checkForErrorsInUserCreateMethodParams("valid_login_12f", "valid_pass_123"),
                null)
        assertEquals(MethodInvokesValidator.checkForErrorsInUserCreateMethodParams("valid_login_12f", "@invalid_pass_123"),
                UserCreateMethodReturnCodes.INVALID_PASSWORD_ERROR)
        assertEquals(MethodInvokesValidator.checkForErrorsInUserCreateMethodParams("valid_login_12f", "invalid!"),
                UserCreateMethodReturnCodes.INVALID_PASSWORD_ERROR)
    }
}