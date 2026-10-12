/*
Create a Servlet that reads parameters from an HTML form and generates an appropriate HTML response using HttpServletRequest and HttpServletResponse.
*/
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student-response")
public class StudentResponseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<html><body><h1>Student Details</h1><p>Name: "
                + escape(request.getParameter("name")) + "</p><p>Course: "
                + escape(request.getParameter("course")) + "</p></body></html>");
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
}
