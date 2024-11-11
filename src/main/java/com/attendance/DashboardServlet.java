package com.attendance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import org.json.JSONObject;
import org.json.JSONArray;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        JSONObject dashboardData = new JSONObject();
        JSONArray attendanceData = new JSONArray();
        JSONArray payrollData = new JSONArray();

        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement()) {

            // Fetch attendance data
                        // Fetch attendance data
                        ResultSet attendanceResultSet = stmt.executeQuery("SELECT COUNT(*) AS total_attendance FROM attendance WHERE present = 1");
                        if (attendanceResultSet.next()) {
                            dashboardData.put("total_attendance", attendanceResultSet.getInt("total_attendance"));
                        }
            
                        // Fetch payroll data
                        ResultSet payrollResultSet = stmt.executeQuery("SELECT SUM(salary) AS total_payroll FROM staff");
                        if (payrollResultSet.next()) {
                            dashboardData.put("total_payroll", payrollResultSet.getDouble("total_payroll"));
                        }
            
                        // Prepare JSON response
                        response.getWriter().write(dashboardData.toString());
            
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            
