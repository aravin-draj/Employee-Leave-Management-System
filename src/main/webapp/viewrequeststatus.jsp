<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.leavemanagement.bean.LeaveRequestBean" %>
    
<%
 LeaveRequestBean leaveRequest = (LeaveRequestBean) request.getAttribute("leaveRequest");
    if (leaveRequest == null) {
        response.sendRedirect("EmployeeDashboard.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Leave Request Status - <%= leaveRequest.getStatus() %></h2>
	<p>Applied On: <%= leaveRequest.getAppliedOn() %></p>
    <p>Employee ID: <%= leaveRequest.getEmpId() %></p>
    <p>Leave ID: <%= leaveRequest.getLeaveId() %></p>
    <p>From Date: <%= leaveRequest.getFromDate() %></p>
    <p>To Date: <%= leaveRequest.getToDate() %></p>
    <p>Reason: <%= leaveRequest.getReason() %></p>
    <p><a href="EmployeeDashboard.jsp">Back to Dashboard</a></p>
</body>
</html>