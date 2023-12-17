package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String name= request.getParameter("name");
	  String email= request.getParameter("email");
	  String password= request.getParameter("password");
	  try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection conn =  DatabaseConnection.getConnection();
		  String query = "insert into users(name, email,password) values (?,?,?) ";
		  PreparedStatement pstmt= conn.prepareStatement(query);
		  pstmt.setString(1,name);
		  pstmt.setString(2, email);
		  pstmt.setString(3, password);
		  pstmt.executeUpdate();
		  conn.close();
		  response.sendRedirect("confirmation.jsp");
		  
	  }catch (ClassNotFoundException |SQLException e) 
	  {
	  e.printStackTrace();
	  }
	  }
	  }
