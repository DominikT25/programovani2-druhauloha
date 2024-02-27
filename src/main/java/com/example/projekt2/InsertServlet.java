package com.example.projekt2;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/insert")
public class InsertServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String val = request.getParameter("val");
        if(val==null|| val.isEmpty()){
            out.println("Chybí parametr");
            return;
        }
        String sql = "INSERT INTO users (name) VALUES (?)";
        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, val);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            out.println("Řádek vložen");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}