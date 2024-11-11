package com.attendance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/IncomeExpenseServlet")
public class IncomeExpenseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        double amount = Double.parseDouble(request.getParameter("amount"));

        try (Connection connection = DatabaseUtil.getConnection()) {
            String query = "INSERT INTO financials (type, amount, date) VALUES (?, ?, NOW())";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, type);
            stmt.setDouble(2, amount);
            stmt.executeUpdate();

            response.sendRedirect("income_expense.html?success=Transaction+recorded");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("income_expense.html?error=Error+recording+transaction");
        }
    }
}
