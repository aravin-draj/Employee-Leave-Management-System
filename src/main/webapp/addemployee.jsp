<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Employee</title>
</head>
<body>
    <h1>Add New Employee</h1>

    <form action="AddEmployee" method="post">
        <label for="name">Name:</label>
        <input type="text" name="name" id="name" required><br><br>

        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required><br><br>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required><br><br>

        <label for="deptId">Department ID:</label>
        <input type="number" name="deptId" id="deptId" required><br><br>

        <label for="role">Role:</label>
        <select name="role" id="role" required>
            <option value="">--Select Role--</option>
            <option value="employee">Employee</option>
            <option value="manager">Manager</option>
        </select><br><br>

        <input type="submit" value="Add Employee">
    </form>
</body>
</html>
