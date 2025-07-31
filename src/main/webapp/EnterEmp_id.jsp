<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Enter Employee ID</title>
</head>
<body>
    <h1>Enter Employee ID</h1>
    <form action="ViewLeaveHistory" method="post">
        <label for="empId">Employee ID:</label>
        <input type="text" id="empId" name="empId" required>
        <br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
