/*
Create a Servlet that displays student information and deploy it on a servlet container such as Apache Tomcat.
*/
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<html><body><h1>Student Information</h1>"
                + "<p>Name: Asha</p><p>Student ID: 1</p><p>Course: Computer Science</p>"
                + "</body></html>");
    }
}
