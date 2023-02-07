package jm.task.core.jdbc.util;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class Util {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String HOST = "jdbc:postgresql://localhost:5432/test_table?serverTimezone=Europe/Moscow";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST, LOGIN, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

//Class.forName("org.postgresql.Driver");
//            String URL = "jdbc:postgresql://localhost:5432/test_table";
//            String LOGIN = "root";
//            String PASSWORD = "root";
//    public static Connection connectDB() throws SQLException {
//        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//        return connection;
//    }

