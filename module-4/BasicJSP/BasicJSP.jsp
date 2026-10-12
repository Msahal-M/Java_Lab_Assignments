<%-- Create a JSP page that displays dynamic student information and demonstrate the JSP translation, compilation and execution process. --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String studentName = "Asha";
    int studentId = 1;
    String course = "Java";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Basic JSP</title>
</head>
<body>
    <h1>Student Information</h1>
    <p>Name: <%= studentName %></p>
    <p>Student ID: <%= studentId %></p>
    <p>Course: <%= course %></p>
    <h2>JSP Life Cycle</h2>
    <ol>
        <li>Tomcat translates this JSP into a servlet.</li>
        <li>Tomcat compiles the servlet.</li>
        <li>The servlet executes to create this response.</li>
    </ol>
</body>
</html>
