package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    // реализуйте настройку соеденения с БД useSSL=false&serverTimezone=UTC
    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_jdbc";
    private static final String DB_USERNAME = "bestuser";
    private static final String DB_PASSWORD = "bestuser";
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection OK!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR!");
        }
    }
}