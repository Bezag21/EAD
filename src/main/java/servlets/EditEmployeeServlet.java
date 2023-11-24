package servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EditEmployeeServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("edit_employee.html").include(request, response);
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
//        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String name = request.getParameter("name");
        String designation = request.getParameter("designation");
        double salary = Double.parseDouble(request.getParameter("salary"));
        
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            try {
				connection = DatabaseConnection.getConnection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
            String sql = "UPDATE employees SET designation=?, salary=? WHERE name=?";
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, designation);
            statement.setDouble(2, salary);
            statement.setString(3, name);
//            statement.setInt(4, employeeI
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