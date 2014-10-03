package DAO

import ServerConfiguration.ServerConfiguration
import LoggingUtils.Loggers
import java.sql.Connection
import java.sql.DriverManager
import DAO.DataClasses.User
import DAO.DataClasses.Note

public class DAO() {
    private val connection: Connection
    {
        Class.forName(ServerConfiguration.getProperty("jdbcDriverName") as String)
        connection = DriverManager.getConnection(ServerConfiguration.getProperty("connectionToDB") as String)
        Loggers.db.info("Data Access Object was initialized")
    }

    private fun dropAllUsedTables() {
        throw UnsupportedOperationException()
    }

    private fun createAllTables() {
        throw UnsupportedOperationException()
    }

    private fun getUser(login: String): User? {
        throw UnsupportedOperationException()
    }

    private fun getNoteIdsForUser(user: User): List<Long> {
        throw UnsupportedOperationException()
    }

    private fun getAllNotesWithIdsForUser(user: User, noteIds: List<Long>): List<Note> {
        throw UnsupportedOperationException()
    }
}