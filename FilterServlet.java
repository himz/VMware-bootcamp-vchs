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

	private final static String QUEUE_NAME = "hello";

	public void init() throws ServletException {
	}

	private void sendToRabbitMQ(String message) throws java.io.IOException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		channel.close();
		connection.close();
	}

	// create the JSON-like string that is sent to RabbitMQ
	private String formMessage(HttpServletRequest req) {
		String message = "{\n";
		try {
			// Get an UserAgentStringParser and analyze the requesting client
			Parser uaParser = new Parser();
			Client c = uaParser.parse(req.getHeader("user-agent"));
			Calendar cal = Calendar.getInstance();

			message += "\"year\":\"" + cal.get(Calendar.YEAR) + "\",\n";
			message += "\"month\":\"" + (cal.get(Calendar.MONTH) + 1)  + "\",\n"; //+1 because jan is 0
			message += "\"day\":\"" + cal.get(Calendar.DAY_OF_MONTH)  + "\",\n";
			message += "\"hour\":\"" + cal.get(Calendar.HOUR_OF_DAY)  + "\",\n";
			message += "\"min\":\"" + cal.get(Calendar.MINUTE)  + "\",\n";
			message += "\"sec\":\"" + cal.get(Calendar.SECOND)  + "\",\n";
			message += "\"browser\":\"" + c.userAgent.family  + "\",\n";
			message += "\"OS\":\"" + c.os.family  + "\",\n";
			message += "\"http_verb\":\"" + req.getMethod()  + "\",\n";
			message += "\"api_path\":\"" + req.getRequestURL()  + "\",\n";
			message += "\"client_ip\":\"" + req.getRemoteAddr()  + "\",\n";
			message += "\"response_time\":\"100\"\n";

			message += "}";
		} catch (Exception e) {
			message = e.toString();
		}
		return message;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");

		//Send the request to the actual source
		
		//Computer response time
		
		//Persist to DB
		sendToRabbitMQ(formMessage(request));

		// Send the response
		PrintWriter out = response.getWriter();
		out.println("<h1>" + request.getRequestURL() + "</h1>");
		out.println("<h1>" + request.getHeader("User-Agent") + "</h1>");

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void destroy() {
		// do nothing.
	}
}
