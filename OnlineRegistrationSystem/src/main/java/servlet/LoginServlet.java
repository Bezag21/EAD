package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.DatabaseConnection;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn =  DatabaseConnection.getConnection();
			String query = "select * from users where email = ? AND password = ?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setString(1, email);
					pstmt.setString(2, password);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
					    String name = getUserFullNameFromDatabase(email);
					    HttpSession session = request.getSession();
					    session.setAttribute("name", name);
					    conn.close();
					    response.sendRedirect("welcome.jsp");
					} else {
					    conn.close();
					    response.sendRedirect("login.jsp");
					}
					}catch(ClassNotFoundException|SQLException e) {
					e.printStackTrace();
					}
					}
	private String getUserFullNameFromDatabase(String email) throws ClassNotFoundException {
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement("SELECT name FROM users WHERE email = ?")) {

	        statement.setString(1, email);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            return resultSet.getString("name");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null; 
	    
	}
	
					}