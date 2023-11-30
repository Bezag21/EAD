package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class DeleteEmployeeServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	request.getRequestDispatcher("delete_employee.html").include(request, response);
//
//    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String id = request.getParameter("id");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            try {
				connection =  DatabaseConnection.getConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            String sql = "DELETE FROM employees WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
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