<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String role = request.getParameter("role");
    String actionUrl = "#";

    if ("employee".equals(role)) {
        actionUrl = "EmployeeLogin";
    } else if ("manager".equals(role)) {
        actionUrl = "ManagerLogin";
    } else if ("admin".equals(role)) {
        actionUrl = "AdminLogin";
    } else {
        out.println("<h3>Invalid role selected. Please go back and try again.</h3>");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= role.substring(0, 1).toUpperCase() + role.substring(1) %> Login</title>
</head>
<body>
    <h2><%= role.substring(0, 1).toUpperCase() + role.substring(1) %> Login</h2>

    <form action="<%= actionUrl %>" method="post">
        <label>Email:</label>
        <input type="text" name="email" required><br><br>

        <label>Password:</label>
        <input type="password" name="password" required><br><br>

        <input type="submit" value="Login">
    </form>
</body>
</html>
