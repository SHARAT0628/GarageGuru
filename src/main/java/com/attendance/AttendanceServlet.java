package com.attendance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int staffId = Integer.parseInt(request.getParameter("staff_id"));
        boolean present = Boolean.parseBoolean(request.getParameter("present"));

        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO attendance (staff_id, date, present) VALUES (?, CURDATE(), ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, staffId);
            stmt.setBoolean(2, present);
            stmt.executeUpdate();

            response.sendRedirect("attendance.html?success=Attendance+recorded");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("attendance.html?error=Error+recording+attendance");
        }
    }
}
