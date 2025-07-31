<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Message</title>
</head>
<body>
    <p><%= request.getAttribute("message") %></p>

    <% 
        String redirectURL = (String) request.getAttribute("redirectURL");
        String linkText = (String) request.getAttribute("linkText");
        if (redirectURL != null && linkText != null) {
    %>
        <p><a href="<%= redirectURL %>"><%= linkText %></a></p>
    <% } else { %>
        <p><a href="index.jsp">Return to Home</a></p>
    <% } %>
</body>
</html>
