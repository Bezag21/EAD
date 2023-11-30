package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EditEmployeeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    int id = Integer.parseInt(request.getParameter("id"));

	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE id=?")) {
	        statement.setInt(1, id);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                String name = resultSet.getString("name");
	                String salary = resultSet.getString("salary");
	                String designation = resultSet.getString("designation");

	                // Write HTML dynamically
	                response.setContentType("text/html");
	                PrintWriter out = response.getWriter();
	                out.println("<!DOCTYPE html>");
	                out.println("<html>");
	                out.println("<head>");
	                out.println("<title>Edit Employee</title>");
	                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
	                out.println("</head>");
	                out.println("<body>");
	                out.println("<nav>");
	                out.println("<ul>");
	                out.println("<li><a href=\"ViewEmployeesServlet\">BACK</a></li>");
	                out.println("<li><a href=\"add_employee.html\">ADD</a></li>");
	                out.println("</ul>");
	                out.println("</nav>");
	                out.println("<h1>Edit Employee</h1>");
	                out.println("<form action=\"EditEmployeeServlet\" method=\"post\">");
	                out.println("<input type=\"hidden\" name=\"id\" value=\"" + id + "\">");
	                out.println("<label for=\"name\">Name:</label>");
	                out.println("<input type=\"text\" name=\"name\" value=\"" + name + "\">");
	                out.println("<br>");
	                out.println("<label for=\"salary\">Salary:</label>");
	                out.println("<input type=\"text\" name=\"salary\" value=\"" + salary + "\">");
	                out.println("<br>");
	                out.println("<label for=\"designation\">Designation:</label>");
	                out.println("<input type=\"text\" name=\"designation\" value=\"" + designation + "\">");
	                out.println("<br>");
	                out.println("<input type=\"submit\" value=\"Update\">");
	                out.println("</form>");
	                out.println("</body>");
	                out.println("</html>");

	            } else {
	                response.sendRedirect("ViewEmployeesServlet");
	            }
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request");
	    }
	}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String salary = request.getParameter("salary");
        String designation = request.getParameter("designation");

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE employees SET name = ?, salary = ?, designation = ? WHERE id = ?")) {
            statement.setString(1, name);
            statement.setString(2, salary);
            statement.setString(3, designation);
            statement.setInt(4, id);
            statement.executeUpdate();
            response.sendRedirect("ViewEmployeesServlet");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request");
        }
    }
}