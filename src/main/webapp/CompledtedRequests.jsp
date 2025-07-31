<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.leavemanagement.bean.LeaveRequestBean, com.leavemanagement.bean.EmployeeBean" %>
<!DOCTYPE html>
<html>
<head>
    <title>Completed Leave Requests</title>
</head>
<body>
    <h1>Completed Leave Requests</h1>

    <%
        List<LeaveRequestBean> completedRequests = (List<LeaveRequestBean>) request.getAttribute("completedrequests");
        List<EmployeeBean> emp_details = (List<EmployeeBean>) request.getAttribute("emp_details");

        if (completedRequests != null && !completedRequests.isEmpty()) {
    %>
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>Employee Name</th>
                <th>Email</th>
                <th>Employee ID</th>
                <th>Leave ID</th>
                <th>From Date</th>
                <th>To Date</th>
                <th>No of Days</th>
                <th>Reason</th>
                <th>Status</th>
                <th>Decision Date</th>
            </tr>
            <%
                for (int i = 0; i < completedRequests.size(); i++) {
                    LeaveRequestBean leave = completedRequests.get(i);
                    EmployeeBean emp = emp_details.get(i);
            %>
                <tr>
                    <td><%= emp.getName() %></td>
                    <td><%= emp.getEmail() %></td>
                    <td><%= leave.getEmpId() %></td>
                    <td><%= leave.getLeaveId() %></td>
                    <td><%= leave.getFromDate() %></td>
                    <td><%= leave.getToDate() %></td>
                    <td><%= leave.getNumDays() %></td>
                    <td><%= leave.getReason() %></td>
                    <td><%= leave.getStatus() %></td>
                    <td><%= leave.getDecisionDate() %></td>
                </tr>
            <%
                }
            %>
        </table>
    <%
        } else {
    %>
        <p>No completed leave requests found.</p>
    <%
        }
    %>

</body>
</html>
