package ServerConfiguration

import java.util.Properties

object ServerConfiguration {
    private val properties: Properties

    {
        val inputStream = javaClass.getResourceAsStream("/cfg/settings.properties")
        properties = Properties()
        properties.load(inputStream);
    }

    public fun getProperty(propertyKey: String): Any {
        val propertiesValue = properties.get(propertyKey)
        if (propertiesValue == null)
            throw RuntimeException("No such property: $propertyKey")
        val propertiesValueWithAppropriateType =
                recreateWithAppropriateType(propertyKey, propertiesValue as String)
        return propertiesValueWithAppropriateType
    }

    private fun recreateWithAppropriateType(propertyKey: String, propertyValue: String): Any {
        return when (propertyKey) {
            "dropDBOnStart" -> if (propertyValue == "true") true else false
            "port" -> Integer.parseInt(propertyValue)
            "entry" -> propertyValue
            "jdbcDriverName" -> propertyValue
            "connectionToDB" -> propertyValue
            else -> throw RuntimeException("Unknown property key: $propertyKey with value $propertyValue")
        }
    }
}