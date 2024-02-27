package com.example.projekt2;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/")
public class DefaultServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            Connection connection = DatabaseUtil.getConnection();
            DatabaseUtil.createTable(connection);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        out.println("<html><body><a href=\"home\">Home</a></body></html>");
    }


}