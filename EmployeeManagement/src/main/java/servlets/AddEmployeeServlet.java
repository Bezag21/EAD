package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class AddEmployeeServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.getRequestDispatcher("add_employee.html").include(request, response);

}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String name = request.getParameter("name");
    String designation = request.getParameter("designation");
    double salary = Double.parseDouble(request.getParameter("salary"));
    try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	} catch (ClassNotFoundException cnf) {
		cnf.printStackTrace();
	}

    Connection connection = null;
    PreparedStatement statement = null;
    try {

			try {
				connection =  DatabaseConnection.getConnection();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}

        String sql = "INSERT INTO employees (name, designation, salary) VALUES (?, ?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, designation);
        statement.setDouble(3, salary);
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
    	if (statement != null) {
    	    try {
    	        statement.close();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}

    	// Close the connection
    	if (connection != null) {
    	    try {
    	        connection.close();
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	}
    }
    response.sendRedirect("ViewEmployeesServlet");
}
}
