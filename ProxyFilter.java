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

// Extend Filter class
public class ProxyFilter implements Filter {

	private String rabbitMQName;
	private String rabbitMQServer;
	FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		rabbitMQName = filterConfig.getInitParameter("rabbitmq-name");
		rabbitMQServer = filterConfig.getInitParameter("rabbitmq-server");
	}

	public void destroy() {
	}

	private void sendToRabbitMQ(String message) throws java.io.IOException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(rabbitMQServer);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(rabbitMQName, false, false, false, null);
		channel.basicPublish("", rabbitMQName, null, message.getBytes());
		channel.close();
		connection.close();
	}

	// create the JSON-like string that is sent to RabbitMQ
	private String formMessage(HttpServletRequest req, long timeInMs) {
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
			message += "\"response_time\":\"" + timeInMs + "\"\n";

			message += "}";
		} catch (Exception e) {
			message = "Exception in formMessage " + e.toString();
		}
		return message;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
		long startTime = System.currentTimeMillis(); 

		//Send the request to the actual source
		filterChain.doFilter(request,response);

		long endTime = System.currentTimeMillis(); 

               // have to cast here, couldn't find a better way and not sure if this is safe ... but it works
		sendToRabbitMQ(formMessage((HttpServletRequest)request, endTime-startTime));

		} catch (Exception e) {
			response.getWriter().println(e.toString());
		}
	}

}
