package com.attendance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/PayrollServlet")
public class PayrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = DatabaseUtil.getConnection();
             Statement stmt = connection.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT staff.name, COUNT(attendance.present) AS present_days, staff.salary FROM attendance JOIN staff ON attendance.staff_id = staff.id WHERE attendance.present = 1 GROUP BY staff.id");

            StringBuilder payrollData = new StringBuilder();
            payrollData.append("<table><tr><th>Name</th><th>Salary</th></tr>");

            while (rs.next()) {
                String name = rs.getString("name");
                int presentDays = rs.getInt("present_days");
                double salary = rs.getDouble("salary");

                double calculatedSalary = (salary / 30) * presentDays; // Salary calculated based on present days
                payrollData.append("<tr><td>").append(name).append("</td><td>").append(calculatedSalary).append("</td></tr>");
            }

            payrollData.append("</table>");
            response.getWriter().write(payrollData.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
