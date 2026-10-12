<%-- Create a JSP page that uses declarations and expressions to calculate and display student marks, total and average. --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%!
    int calculateTotal(int first, int second, int third) {
        return first + second + third;
    }
%>
<%
    String studentName = "Ravi";
    int mark1 = 82;
    int mark2 = 76;
    int mark3 = 91;
    int total = calculateTotal(mark1, mark2, mark3);
    double average = total / 3.0;
%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Marks</title>
</head>
<body>
    <h1>Marks for <%= studentName %></h1>
    <p>Subject 1: <%= mark1 %></p>
    <p>Subject 2: <%= mark2 %></p>
    <p>Subject 3: <%= mark3 %></p>
    <p>Total: <%= total %></p>
    <p>Average: <%= average %></p>
</body>
</html>
