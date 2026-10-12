/*
Develop a simple login application with protected resources where only authenticated users can access a particular Servlet or page.
*/
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login", "/protected"})
public class SecurityLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        if (request.getServletPath().equals("/protected")) {
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("username") == null) {
                response.sendRedirect("login");
                return;
            }

            response.getWriter().println("<h1>Protected Page</h1>");
            response.getWriter().println("<p>You are logged in.</p>");
            return;
        }

        response.getWriter().println("<h1>Login</h1>");
        response.getWriter().println("<form method='post' action='login'>");
        response.getWriter().println("Username: <input name='username'><br>");
        response.getWriter().println("Password: <input name='password' type='password'><br>");
        response.getWriter().println("<button type='submit'>Login</button></form>");
        response.getWriter().println("<p>Use student and java123.</p>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("student".equals(username) && "java123".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("protected");
        } else {
            response.getWriter().println("<p>Login failed.</p>");
            response.getWriter().println("<a href='login'>Try again</a>");
        }
    }
}
