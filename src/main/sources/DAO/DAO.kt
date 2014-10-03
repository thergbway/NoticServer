package DAO

import ServerConfiguration.ServerConfiguration
import LoggingUtils.Loggers
import java.sql.Connection
import java.sql.DriverManager
import DAO.DataClasses.User
import DAO.DataClasses.Note

public object DAO {
    private val tableNames = listOf("Notes", "Users")
    private val connection: Connection

    {
        Loggers.db.info("Data Access Object initializing")
        Class.forName(ServerConfiguration.getProperty("jdbcDriverName") as String)
        connection = DriverManager.getConnection(ServerConfiguration.getProperty("connectionToDB") as String)

        if (ServerConfiguration.getProperty("dropDBOnStart") as Boolean) {
            Loggers.db.info("Drop data base on start = true. Dropping")
            dropDataBase()
            Loggers.db.info("Creating tables")
            createAllTables()
        }

        Loggers.db.info("Data Access Object was initialized")
    }

    public fun addNewUser(login: String, password: String): String {
        throw UnsupportedOperationException()
        //return "success"
    }

    private fun dropDataBase() {
        val st = connection.createStatement()!!
        tableNames.forEach {
            val sql = "drop table if exists $it;"
            Loggers.db.info("...sql: $sql")
            st.executeUpdate(sql)
        }
    }

    private fun createAllTables() {
        val st = connection.createStatement()!!
        val sqlQueriesToCreateTables = mapOf(
                "Users" to "create table Users(" +
                        "login CHARACTER(100) NOT NULL PRIMARY KEY," +
                        "password CHARACTER(100) NOT NULL" +
                        ");",
                "Notes" to "create table Notes(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "userLogin CHARACTER(100)," +
                        "title CHARACTER(100) NOT NULL," +
                        "text VARCHAR(10000) NOT NULL," +
                        "FOREIGN KEY(userLogin) REFERENCES Users(login)" +
                        ")"
        )
        tableNames.forEach {
            var sql = sqlQueriesToCreateTables.get(it)
            Loggers.db.info("...sql: $sql")
            st.executeUpdate(sql as String)
        }
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