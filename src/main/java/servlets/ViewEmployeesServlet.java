package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewEmployeesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            String sql = "SELECT * FROM employees";
            resultSet = statement.executeQuery(sql);

            // Creating the HTML table dynamically
            StringBuilder tableHtml = new StringBuilder();
            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String salary = resultSet.getString("salary");
                String designation = resultSet.getString("designation");
                tableHtml.append("<tr>");
//                tableHtml.append("<td>").append(id).append("</td>");
                tableHtml.append("<td>").append(name).append("</td>");
                tableHtml.append("<td>").append(salary).append("</td>");
                tableHtml.append("<td>").append(designation).append("</td>");
                tableHtml.append("</tr>");
            }

            // Read the employees.html file
            BufferedReader reader = new BufferedReader(new FileReader("src/main/webapp/view_employees.html"));
            String line;
            StringBuilder htmlContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("<!-- This section will be replaced with dynamic data -->")) {
                    htmlContent.append(tableHtml.toString());
                } else {
                    htmlContent.append(line);
                }
            }
            reader.close();

            response.setContentType("text/html");

          
            response.getWriter().println(htmlContent.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
           
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
