<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ELMS Portal</title>
</head>
<body>
    <h1>Welcome to ELMS Portal</h1>
    <p>Select your role to continue:</p>

    <form action="login.jsp" method="get">
        <input type="hidden" name="role" value="employee">
        <input type="submit" value="Employee">
    </form>

    <form action="login.jsp" method="get">
        <input type="hidden" name="role" value="manager">
        <input type="submit" value="Manager">
    </form>

    <form action="login.jsp" method="get">
        <input type="hidden" name="role" value="admin">
        <input type="submit" value="Admin">
    </form>
</body>
</html>
