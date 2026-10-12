<%-- Create JSP pages demonstrating important page directive attributes such as import, contentType and errorPage. --%>
<%@ page import="java.util.Date" contentType="text/html; charset=UTF-8" errorPage="DirectiveError.jsp" %>
<%
    String mode = request.getParameter("mode");
    if ("error".equals(mode)) {
        throw new ArithmeticException("Requested demonstration error");
    }
    Date today = new Date();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Page Directives</title>
</head>
<body>
    <h1>Page Directive Attributes</h1>
    <p>The import attribute makes LocalDate available to this JSP.</p>
    <p>The contentType attribute sets this response to HTML with UTF-8 encoding.</p>
    <p>Today: <%= today %></p>
    <p>The errorPage attribute sends exceptions to DirectiveError.jsp.</p>
    <p><a href="Directives.jsp?mode=error">Test the error page</a></p>
</body>
</html>
