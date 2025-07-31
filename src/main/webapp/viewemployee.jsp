<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin View</title>
</head>
<body>
    <h1>Enter Employee ID</h1>
    <form action="ViewEmployee" method="get">
        <label for="empId">Employee ID:</label>
        <input type="text" id="empId" name="empId" required>
        <br><br>
        <input type="submit" value="Submit">
    </form>
    <br>
    
    <h1>Or View All Employee</h1>

    <form action="ViewAllEmployee" method="get">
        <input type="submit" value="View All Employees">
    </form>
    <br>
</body>
</html>
