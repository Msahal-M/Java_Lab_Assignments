<%-- Develop a JSP page demonstrating commonly used implicit objects such as request, response, session, application, out, config, pageContext and exception. --%>
<%@ page contentType="text/html; charset=UTF-8" errorPage="ImplicitError.jsp" %>
<%
    if ("yes".equals(request.getParameter("fail"))) {
        Integer.parseInt("not-a-number");
    }
    String name = request.getParameter("name");
    if (name == null) {
        name = "No name supplied";
    }
    session.setAttribute("studentName", "Mina");
    application.setAttribute("courseName", "Java");
    response.setHeader("X-Assignment", "JSPImplicitObjects");
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP Implicit Objects</title>
</head>
<body>
    <h1>Implicit Objects</h1>
    <p>request parameter: <%= name %></p>
    <p>response content type: <%= response.getContentType() %></p>
    <p>session student: <%= session.getAttribute("studentName") %></p>
    <p>application course: <%= application.getAttribute("courseName") %></p>
    <p>out writes this message: <% out.print("Hello from out."); %></p>
    <p>config servlet name: <%= config.getServletName() %></p>
    <p>pageContext context path: <%= pageContext.getRequest().getServletContext().getContextPath() %></p>
    <p>page object: <%= page.getClass().getSimpleName() %></p>
    <p><a href="JSPImplicitObjects.jsp?fail=yes">Test the error page and exception object</a></p>
</body>
</html>
