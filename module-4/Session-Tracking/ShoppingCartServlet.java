/*
Develop a simple shopping cart/session-based application using HttpSession to maintain user information across multiple requests.
*/
import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class ShoppingCartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String items = (String) session.getAttribute("items");

        if (items == null) {
            items = "Cart is empty";
        }

        response.setContentType("text/html");
        response.getWriter().println("<h1>Shopping Cart</h1>");
        response.getWriter().println("<p>Items: " + items + "</p>");
        response.getWriter().println("<form method='post' action='cart'>");
        response.getWriter().println("<button name='item' value='Book'>Add Book</button>");
        response.getWriter().println("<button name='item' value='Pen'>Add Pen</button>");
        response.getWriter().println("<button name='item' value='clear'>Clear Cart</button>");
        response.getWriter().println("</form>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String item = request.getParameter("item");
        String items = (String) session.getAttribute("items");

        if (item.equals("clear")) {
            session.setAttribute("items", "Cart is empty");
        } else if (items == null || items.equals("Cart is empty")) {
            session.setAttribute("items", item);
        } else {
            session.setAttribute("items", items + ", " + item);
        }

        response.sendRedirect("cart");
    }
}
