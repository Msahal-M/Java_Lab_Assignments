<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Implicit Objects Error Page</title>
</head>
<body>
    <h1>Error Page</h1>
    <p>Exception: <%= exception.getClass().getSimpleName() %></p>
    <p>Message: <%= exception.getMessage() %></p>
</body>
</html>
