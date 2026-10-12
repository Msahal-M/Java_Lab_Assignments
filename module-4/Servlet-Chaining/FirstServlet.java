/*
Create two Servlets where the first Servlet processes a request and forwards or includes the request to the second Servlet using RequestDispatcher.
*/
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        request.setAttribute("message", "Hello " + name);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/second");
        dispatcher.forward(request, response);
    }
}
