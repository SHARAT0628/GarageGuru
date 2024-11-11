package com.attendance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/attendance_management";
    private static final String USER = "root"; // Change as per your setup
    private static final String PASSWORD = "password"; // Change as per your setup

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void executeSqlScript(String filePath) {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            StringBuilder sql = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sql.append(line);
                sql.append(System.lineSeparator());
            }

            // Execute the SQL commands
            stmt.execute(sql.toString());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
    