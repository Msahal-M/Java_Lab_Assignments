<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List,studentapp.Student" %>
<%
    Student student = (Student) request.getAttribute("student");
    List<Student> students = (List<Student>) request.getAttribute("students");
%>
<!DOCTYPE html>
<html>
<head><title>Student Management</title></head>
<body>
    <h1>Student Management</h1>
    <% if (student == null) { %>
        <h2>Add Student</h2>
        <form method="post" action="students">
            <input type="hidden" name="action" value="add">
            Name: <input name="name">
            Course: <input name="course">
            <button type="submit">Add</button>
        </form>
    <% } else { %>
        <h2>Update Student</h2>
        <form method="post" action="students">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="<%= student.getId() %>">
            Name: <input name="name" value="<%= student.getName() %>">
            Course: <input name="course" value="<%= student.getCourse() %>">
            <button type="submit">Update</button>
        </form>
    <% } %>

    <h2>Students</h2>
    <table border="1">
        <tr><th>ID</th><th>Name</th><th>Course</th><th>Actions</th></tr>
        <% if (students != null) { %>
            <% for (Student item : students) { %>
                <tr>
                    <td><%= item.getId() %></td>
                    <td><%= item.getName() %></td>
                    <td><%= item.getCourse() %></td>
                    <td>
                        <a href="students?action=edit&id=<%= item.getId() %>">Edit</a>
                        <form method="post" action="students">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%= item.getId() %>">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        <% } %>
    </table>
</body>
</html>
