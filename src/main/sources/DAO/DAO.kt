package DAO

import WebServer.MethodInvokeResultCodes.UserCreateMethodReturnCodes

import ServerConfiguration.ServerConfiguration
import LoggingUtils.Loggers
import java.sql.Connection
import java.sql.DriverManager
import DAO.DataClasses.User
import DAO.DataClasses.Note
import java.util.LinkedList

public object DAO {
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

    public fun addNewUser(login: String, password: String): UserCreateMethodReturnCodes {
        if (isUserExisting(login))
            return UserCreateMethodReturnCodes.USER_EXISTS_ERROR

        val st = connection.createStatement()!!
        val sql = "insert into Users (login, password) values ('$login', '$password');"
        Loggers.db.info("...sql: $sql")
        st.executeUpdate(sql)
        st.close()

        return UserCreateMethodReturnCodes.SUCCESS
    }

    private fun dropDataBase() {
        val st = connection.createStatement()!!
        listOf("Notes", "Users").forEach {
            val sql = "drop table if exists $it;"
            Loggers.db.info("...sql: $sql")
            st.executeUpdate(sql)
        }
        st.close()
    }

    private fun createAllTables() {
        val st = connection.createStatement()!!
        listOf("create table Users(" +
                "login CHARACTER(100) NOT NULL PRIMARY KEY," +
                "password CHARACTER(100) NOT NULL" +
                ");",
                "create table Notes(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "userLogin CHARACTER(100)," +
                        "title CHARACTER(100) NOT NULL," +
                        "text VARCHAR(10000) NOT NULL," +
                        "FOREIGN KEY(userLogin) REFERENCES Users(login)" +
                        ")").forEach {
            var sql = it
            Loggers.db.info("...sql: $sql")
            st.executeUpdate(sql)
        }
        st.close()
    }

    private fun isUserExisting(login: String): Boolean {
        val st = connection.createStatement()!!
        val sql = "select * from Users where login = '$login'"
        Loggers.db.info("...sql: $sql")
        val rs = st.executeQuery(sql)
        val result = rs.next()
        st.close()
        return result
    }

    private fun getUser(login: String): User {
        val st = connection.createStatement()!!
        val sql = "select login, password from Users where login = '$login';"
        Loggers.db.info("...sql: $sql")
        val rs = st.executeQuery(sql)
        rs.next()
        val login = rs.getString("login")
        val password = rs.getString("password")
        st.close()
        return User(login, password)
    }

    private fun getNoteIdsForUser(user: User): List<Long> {
        val st = connection.createStatement()!!
        val sql = "select id from Notes where userLogin = '${user.login}';"
        Loggers.db.info("...sql: $sql")
        val rs = st.executeQuery(sql)
        val noteIds = LinkedList<Long>()
        while (rs.next())
            noteIds.add(rs.getLong("id"))
        st.close()
        return noteIds
    }

    private fun isAllNoteIdsExisting(noteIds: List<Long>): Boolean {
        val st = connection.createStatement()!!
        var result = true
        for (id in noteIds) {
            //not very effective way to check
            val sql = "select id from Notes where id = $id;"
            Loggers.db.info("...sql: $sql")
            val rs = st.executeQuery(sql)
            if (!rs.next()) {
                result = false;
                break
            }
        }
        st.close()
        return result
    }

    private fun isAllNoteIdsCorrespondsToUser(noteIds: List<Long>, user: User): Boolean {
        val st = connection.createStatement()!!
        var result = true
        for (id in noteIds) {
            //not very effective way to check
            val sql = "select userLogin from Notes where id = $id;"
            Loggers.db.info("...sql: $sql")
            val rs = st.executeQuery(sql)
            rs.next()
            if (rs.getString("userLogin") != user.login) {
                result = false
                break
            }
        }
        st.close()
        return result
    }

    private fun getAllNotesWithIds(noteIds: List<Long>): List<Note> {
        val st = connection.createStatement()!!
        val sb = StringBuilder()
        sb.append("select * from Notes where")
        with(sb) {
            noteIds.forEach {
                append(" id = $it or")
            }
        }
        sb.delete(sb.length() - 1 - 3, sb.length() - 1)
        sb.append(";")
        val sql = sb.toString()
        Loggers.db.info("...sql: $sql")
        val rs = st.executeQuery(sql)
        val noteList = LinkedList<Note>()
        while (rs.next())
            with(noteList) {
                val id = rs.getLong("id")
                val userLogin = rs.getString("userLogin")
                val title = rs.getString("title")
                val text = rs.getString("text")
                noteList.add(Note(id, userLogin, title, text))
            }
        return noteList
    }
}