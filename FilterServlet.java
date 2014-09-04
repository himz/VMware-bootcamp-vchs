// Import required java libraries
import java.lang.Thread;
import java.util.Enumeration;
import java.util.Calendar;
import java.util.Random;
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

		Random rng = new Random();
		int randomNumber = rng.nextInt(100);
		String URL = new String (request.getRequestURL());
		try {
			if(URL.equals("http://localhost:8080/vApp/power/action/powerOn")) { 
				Thread.sleep(randomNumber);
			} else if(URL.equals("http://localhost:8080/vApp/power/action/powerOff")) { 
				Thread.sleep(randomNumber/2);
			} else if(URL.equals("http://localhost:8080/vApp/power/action/reset")) { 
				Thread.sleep(randomNumber*2);
			} else if(URL.equals("http://localhost:8080/admin/extension/action/register")) { 
				Thread.sleep(randomNumber*5);
			} else if(URL.equals("http://localhost:8080/admin/extension/action/deregister")) {
				Thread.sleep(randomNumber/5);
			} else if(URL.equals("http://localhost:8080/admin/extension/action/getAdmin")) {
				Thread.sleep(randomNumber/10);
			} else if(URL.equals("http://localhost:8080/vApp/action/relocate")) {
				Thread.sleep(randomNumber/10);
			} else if(URL.equals("http://localhost:8080/vApp/action/consolidate")) {
				Thread.sleep(randomNumber/10);
			} else {
				Thread.sleep(randomNumber);
			}
		} catch(Exception e) {
			out.println(e);
		}
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
