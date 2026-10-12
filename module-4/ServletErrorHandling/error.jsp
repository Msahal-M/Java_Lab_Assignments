<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head><title>Custom Error Page</title></head>
<body>
    <h1>Custom Error Page</h1>
    <p><%= exception.getMessage() %></p>
</body>
</html>
