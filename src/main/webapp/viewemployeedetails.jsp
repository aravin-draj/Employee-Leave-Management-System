<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.leavemanagement.bean.EmployeeBean" %>

<%
    EmployeeBean employee = (EmployeeBean) request.getAttribute("employee");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Details</title>
</head>
<body>

    <h2>Employee Details</h2>
    <p><strong>Employee ID:</strong> <%= employee.getEmpId() %></p>
    <p><strong>Name:</strong> <%= employee.getName() %></p>
    <p><strong>Email:</strong> <%= employee.getEmail() %></p>
    <p><strong>Password:</strong> <%= employee.getPassword() %></p>
    <p><strong>Role:</strong> <%= employee.getRole() %></p>
    <p><strong>Department ID:</strong> <%= employee.getDeptId() %></p>

    <br>
    <a href="admindashboard.jsp">Back to Dashboard</a>

</body>
</html>
