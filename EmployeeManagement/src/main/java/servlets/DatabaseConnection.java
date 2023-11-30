package servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = null;

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);


        if (connection != null) {
            System.out.println("Connected to the database.");
        } else {
            System.out.println("Failed to connect to the database.");
        }

        return connection;
    }
}