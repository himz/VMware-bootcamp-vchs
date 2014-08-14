// Import required java libraries
import java.util.Enumeration;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
import com.rabbitmq.client.ConnectionFactory; 
import com.rabbitmq.client.Connection; 
import com.rabbitmq.client.Channel; 

// Extend HttpServlet class
public class FilterServlet extends HttpServlet {
 
  private final static String QUEUE_NAME = "hello";
 
  public void init() throws ServletException
  {
  }
  private void sendToRabbitMQ(String message)
      throws java.io.IOException {
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
    Enumeration<String> headerNames = req.getHeaderNames();
    while (headerNames.hasMoreElements()) {
        String headerName = headerNames.nextElement();
	message += "\"" + headerName + "\":\""; 
        Enumeration<String> headers = req.getHeaders(headerName);
        while (headers.hasMoreElements()) {
		message += headers.nextElement();
        }
	message += "\"\n";
    }
    message += "\n}";
    return message;
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type
      response.setContentType("text/html");
 
      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.println("<h1>" + request.getRequestURL() + "</h1>");
      
      sendToRabbitMQ(formMessage(request));	
  }
  
  public void destroy()
  {
      // do nothing.
  }
}



