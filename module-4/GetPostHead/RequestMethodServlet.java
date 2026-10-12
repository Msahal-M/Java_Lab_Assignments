/*
Create an HTML form and Servlet that handles GET and POST requests. Implement a response for a HEAD request and demonstrate the differences.
*/
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/requests")
public class RequestMethodServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        show(request, response, "GET");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        show(request, response, "POST");
    }

    protected void doHead(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("X-Request-Method", "HEAD");
    }

    private void show(HttpServletRequest request, HttpServletResponse response, String method)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<html><body><h1>" + method + " request</h1><p>Value: "
                + escape(request.getParameter("value")) + "</p></body></html>");
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
}
