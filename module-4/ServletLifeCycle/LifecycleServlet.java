/*
Create a Servlet demonstrating the init(), service() and destroy() life-cycle methods and display messages showing when each method is executed.
*/
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/lifecycle")
public class LifecycleServlet extends HttpServlet {
    public void init() throws ServletException {
        getServletContext().log("init() method executed");
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().log("service() method executed");
        super.service(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<html><body><h1>Servlet Life Cycle</h1>"
                + "<p>init() is called once when the servlet starts.</p>"
                + "<p>service() is called for this request.</p>"
                + "<p>destroy() is logged when the servlet stops.</p></body></html>");
    }

    public void destroy() {
        getServletContext().log("destroy() method executed");
    }
}
