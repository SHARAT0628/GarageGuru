package com.attendance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/AddStaffServlet")
public class AddStaffServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String role = request.getParameter("role");
        double salary = Double.parseDouble(request.getParameter("salary"));

        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO staff (name, role, salary) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, role);
            stmt.setDouble(3, salary);
            stmt.executeUpdate();

            response.sendRedirect("staff.html?success=Staff+added+successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("staff.html?error=Error+adding+staff");
        }
    }
}
