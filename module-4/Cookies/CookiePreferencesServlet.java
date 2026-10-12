/*
Create a Servlet application that stores the user's name or preferences in a cookie and retrieves the information during subsequent requests.
*/
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/preferences")
public class CookiePreferencesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = "No name saved";
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userName")) {
                    userName = cookie.getValue().replace('_', ' ');
                }
            }
        }

        response.setContentType("text/html");
        response.getWriter().println("<h1>Cookie Example</h1>");
        response.getWriter().println("<p>Saved name: " + userName + "</p>");
        response.getWriter().println("<form method='post' action='preferences'>");
        response.getWriter().println("Name: <input name='userName'>");
        response.getWriter().println("<button type='submit'>Save</button></form>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        Cookie cookie = new Cookie("userName", userName.replace(' ', '_'));
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        response.sendRedirect("preferences");
    }
}
