package com.attendance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mobileNumber = request.getParameter("mobileNumber");
        String otp = request.getParameter("otp");

        // Dummy check for demonstration purposes (replace with your logic)
        if (mobileNumber.equals("1234567890") && otp.equals("1234")) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedIn", true);
            response.sendRedirect("index.html");
        } else {
            request.setAttribute("errorMessage", "Invalid mobile number or OTP.");
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }
}
