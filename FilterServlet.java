// Import required java libraries
import java.util.Enumeration;
import java.util.Calendar;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import ua_parser.Parser;
import ua_parser.Client;

// Extend HttpServlet class
public class FilterServlet extends HttpServlet {

	public void init() throws ServletException {
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");

		// Send the response
		PrintWriter out = response.getWriter();
		out.println("<h1>" + request.getRequestURL() + "</h1>");
		out.println("<h1>" + request.getHeader("User-Agent") + "</h1>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void destroy() {
		// do nothing.
	}
}
