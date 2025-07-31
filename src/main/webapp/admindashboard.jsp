<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
</head>
<body>
    <h1>Welcome, Admin</h1>

    <form action="addemployee.jsp" method="get">
        <input type="submit" value="Add Employee">
    </form>
    <br>

    <form action="editemployee.jsp" method="get">
        <input type="submit" value="Update or Delete Employee">
    </form>
    <br>

    <form action="viewemployee.jsp" method="get">
        <input type="submit" value="View Employee">
    </form>
    <br>
</body>
</html>
