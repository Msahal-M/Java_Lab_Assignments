<%-- Create a JSP application using JSTL tags for conditions, iteration and formatting, including c:if, c:forEach, c:choose, c:when and c:otherwise. --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%
    request.setAttribute("marks", java.util.Arrays.asList(78, 92, 84));
    request.setAttribute("average", 84.67);
%>
<!DOCTYPE html>
<html>
<head>
    <title>JSTL Student Marks</title>
</head>
<body>
    <h1>Student Marks</h1>
    <c:if test="${average >= 50}">
        <p>The student has passed.</p>
    </c:if>
    <h2>Marks</h2>
    <ul>
        <c:forEach var="mark" items="${marks}">
            <li><c:out value="${mark}"/></li>
        </c:forEach>
    </ul>
    <p>Average: <fmt:formatNumber value="${average}" minFractionDigits="2" maxFractionDigits="2"/></p>
    <c:choose>
        <c:when test="${average >= 75}">
            <p>Grade: Distinction</p>
        </c:when>
        <c:otherwise>
            <p>Grade: Pass</p>
        </c:otherwise>
    </c:choose>
</body>
</html>
