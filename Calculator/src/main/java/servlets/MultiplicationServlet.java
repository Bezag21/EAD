package servlets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class MultiplicationServlet extends HttpServlet {
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));
        int result = num1 * num2;
        BufferedReader reader = new BufferedReader(new FileReader("src/main/webapp/calculator.html"));
        String line;
        StringBuilder htmlContent = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            if (line.trim().equals("<!-- Result will be displayed here -->")) {
                htmlContent.append(result);
            } else {
                htmlContent.append(line);
            }
        }
        reader.close();
//set the content type of the response
        response.setContentType("text/html");

//write the HTML content to the response
        response.getWriter().println(htmlContent.toString());

    }
}