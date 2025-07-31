<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.leavemanagement.bean.EmployeeBean" %>

<%
    EmployeeBean employee = (EmployeeBean) request.getAttribute("employee");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Employee</title>
</head>
<body>

    <h2>Edit Employee Details</h2>
    <form action="UpdateEmployee" method="post">

        <!-- Hidden field to carry empId -->
        <input type="hidden" name="empId" value="<%= employee.getEmpId() %>">

        <label>Name:</label><br>
        <input type="text" name="name" value="<%= employee.getName() %>" required><br><br>

        <label>Email:</label><br>
        <input type="email" name="email" value="<%= employee.getEmail() %>" required><br><br>

        <label>Password:</label><br>
        <input type="password" name="password" value="<%= employee.getPassword() %>"><br><br>

        <label>Department ID:</label><br>
        <input type="number" name="deptId" value="<%= employee.getDeptId() %>" required><br><br>

        <label>Role:</label><br>
        <select name="role" required>
            <option value="employee" <%= "Employee".equals(employee.getRole()) ? "selected" : "" %>>Employee</option>
            <option value="manager" <%= "Manager".equals(employee.getRole()) ? "selected" : "" %>>Manager</option>
        </select><br><br>

        <input type="submit" value="Update Employee">
    </form>
    <br>
    
    <h1>Or Delete This Employee</h1>

    <form action="DeleteEmployee" method="post">
    <input type="hidden" name="empId" value="<%= employee.getEmpId() %>">
    <input type="submit" value="Delete">
</form>
    <br>

</body>
</html>
