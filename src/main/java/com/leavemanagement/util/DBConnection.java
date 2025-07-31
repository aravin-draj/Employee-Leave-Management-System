package com.leavemanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class DBConnection {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try {
            // Load JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Load properties file from full path (edit this to your exact file path)
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("F:\\Projects\\Employe Leave Management System\\Source\\src\\main\\resources/dbconfig.properties");
            props.load(fis);

            URL = props.getProperty("db.url");
            USER = props.getProperty("db.username");
            PASSWORD = props.getProperty("db.password");

            System.out.println("✅ DB config loaded successfully.");
            System.out.println("URL: " + URL);

        } catch (ClassNotFoundException e) {
            System.out.println("⚠️ JDBC Driver class not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("⚠️ Could not load dbconfig.properties!");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
