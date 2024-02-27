package com.example.projekt2;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/user")
public class UserDetailServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");
        if(id==null|| id.isEmpty()){
            out.println("Chybí parametr");
            return;
        }

        try {
            Integer.parseInt(id);
        }catch (NumberFormatException e){
            out.println("není císlo");
            return;
        }

        String sql = "SELECT * FROM users WHERE id = ?";


        try {
            Connection connection = DatabaseUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                out.println(name + "<br>");
            } else {
                out.println("Nenalezeno");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}