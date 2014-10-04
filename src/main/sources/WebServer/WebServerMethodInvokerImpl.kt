package WebServer

import WebServer.MethodInvokeResultCodes.UserCreateMethodReturnCodes
import DAO.DAO

public class WebServerMethodInvokerImpl() : WebServerMethodInvoker {
    override fun userCreate(login: String, password: String): UserCreateMethodReturnCodes {
        val errorInParamsCheckResult = MethodInvokesValidator.checkForErrorsInUserCreateMethodParams(login, password)
        if (errorInParamsCheckResult != null)
            return errorInParamsCheckResult

        return DAO.addNewUser(login, password)
    }
}