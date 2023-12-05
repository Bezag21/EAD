package servlets;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OperationsServlet extends HttpServlet {
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String operation = request.getParameter("operation");

        if (operation.equals("+")) {
            request.getRequestDispatcher("/AdditionServlet").forward(request, response);
        } else if (operation.equals("*")) {
            request.getRequestDispatcher("/MultiplicationServlet").forward(request, response);
        } else {
            response.getWriter().println("Invalid operation!");
        }
    }
}