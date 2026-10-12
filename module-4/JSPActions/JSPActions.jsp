<%-- Create a JSP application demonstrating standard JSP actions such as jsp:include, jsp:forward, jsp:useBean, jsp:setProperty and jsp:getProperty. --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    if ("yes".equals(request.getParameter("forward"))) {
%>
<jsp:forward page="ForwardTarget.jsp"/>
<%
    }
%>
<jsp:useBean id="student" class="studentactions.StudentBean" scope="page"/>
<jsp:setProperty name="student" property="name" param="name"/>
<jsp:setProperty name="student" property="course" param="course"/>
<!DOCTYPE html>
<html>
<head>
    <title>JSP Actions</title>
</head>
<body>
    <h1>Standard JSP Actions</h1>
    <form method="get">
        <label>Name: <input name="name"></label>
        <label>Course: <input name="course"></label>
        <button type="submit">Set properties</button>
    </form>
    <p>Name: <jsp:getProperty name="student" property="name"/></p>
    <p>Course: <jsp:getProperty name="student" property="course"/></p>
    <jsp:include page="Included.jsp"/>
    <p><a href="JSPActions.jsp?forward=yes">Forward to the target page</a></p>
</body>
</html>
