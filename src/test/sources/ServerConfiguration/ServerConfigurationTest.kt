package ServerConfiguration

import org.junit.Test as test
import kotlin.test.assertTrue
import kotlin.test.fail

class ServerConfigurationTest {
    test fun shouldLoadPortProperty() {
        var port = ServerConfiguration.getProperty("port")
        assertTrue(port is Int)
    }

    test fun shouldThrowExceptionOnNonExistingProperty() {
        try {
            ServerConfiguration.getProperty("notExistingPropertyName")
        } catch (e: Exception) {
            return
        }
        fail("no exception was thrown")
    }
}