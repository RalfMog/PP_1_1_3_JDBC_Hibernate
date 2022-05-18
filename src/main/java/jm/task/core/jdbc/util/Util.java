package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static volatile Util instance;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_jdbc";
    private static final String DB_USERNAME = "bestuser";
    private static final String DB_PASSWORD = "bestuser";
    private static Connection connection;

    private Util() {
    }

    public static Util getInstance() {
        Util localInstance = instance;
        if (localInstance == null) {
            synchronized (Util.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Util();
                }
            }
        }
        return localInstance;
    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeResources(AutoCloseable... resources) {
        {
            for (AutoCloseable resource : resources) {
                if (resource != null) {
                    try {
                        resource.close();
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }
}