<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.leavemanagement.bean.EmployeeBean" %>

<%
    EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
    if (employee == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    String name = employee.getName();
    String role = employee.getRole();
%>

<!DOCTYPE html>
<html>
<head>
    <title>View History</title>
</head>
<body>
     <h1>Manager <%= name%> - Get History of Your</h1>

    <form action="ViewCompletedRequests" method="get">
        <input type="submit" value="Completed Requests">
    </form>

    <form action="EnterEmp_id.jsp" method="get">
        <input type="submit" value="Employee Leave Details">
    </form>
</body>
</html>
