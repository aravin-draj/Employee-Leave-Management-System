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
    <title>Manager Dashboard</title>
</head>
<body>
     <h1>Welcome, <%= name+" "+role%>!</h1>

    <form action="PendingRequests" method="get">
        <input type="submit" value="View Pending Request">
    </form>

    <form action="ViewHistory.jsp" method="get">
        <input type="submit" value="View Leave Request History">
    </form>
</body>
</html>
