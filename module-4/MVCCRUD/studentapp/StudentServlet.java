/*
Develop an MVC-based application to add, view, update and delete student records using JSP, Servlet and JDBC.
*/
package studentapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_db", "root", "root");
            String action = request.getParameter("action");

            if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id, name, course FROM students WHERE id = ?");
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();

                if (result.next()) {
                    Student student = new Student(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getString("course"));
                    request.setAttribute("student", student);
                }
            } else {
                List<Student> students = new ArrayList<Student>();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery("SELECT id, name, course FROM students");

                while (result.next()) {
                    Student student = new Student(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getString("course"));
                    students.add(student);
                }

                request.setAttribute("students", students);
            }

            connection.close();
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_db", "root", "root");

            if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM students WHERE id = ?");
                statement.setInt(1, id);
                statement.executeUpdate();
            } else if ("update".equals(action)) {
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE students SET name = ?, course = ? WHERE id = ?");
                statement.setString(1, request.getParameter("name"));
                statement.setString(2, request.getParameter("course"));
                statement.setInt(3, Integer.parseInt(request.getParameter("id")));
                statement.executeUpdate();
            } else {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO students (name, course) VALUES (?, ?)");
                statement.setString(1, request.getParameter("name"));
                statement.setString(2, request.getParameter("course"));
                statement.executeUpdate();
            }

            connection.close();
            response.sendRedirect("students");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
