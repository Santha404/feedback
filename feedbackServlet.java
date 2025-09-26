package com.example.feedback;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/feedbackdb";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String book = request.getParameter("book");
        String feedback = request.getParameter("feedback");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            String sql = "INSERT INTO feedback(name, book_name, feedback) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, book);
            stmt.setString(3, feedback);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h3>Feedback Saved!</h3>");
        out.println("<a href='feedback.html'>Submit another feedback</a>");
    }
}
