<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.leavemanagement.bean.LeaveRequestBean, com.leavemanagement.bean.EmployeeBean" %>
<%
List<LeaveRequestBean> leavehistory = (List<LeaveRequestBean>) request.getAttribute("leavehistory");
EmployeeBean emp = (EmployeeBean)request.getAttribute("emp_details");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Leave History</title>
</head>
<body>
    <h1>Leave History of <%=emp.getName() %></h1>
    <h2>Email : <%=emp.getName() %></h2>
    <h2>Employee ID : <%=emp.getEmpId() %></h2>

    <%
        if (leavehistory != null && !leavehistory.isEmpty()) {
    %>
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>Leave ID</th>
                <th>From Date</th>
                <th>To Date</th>
                <th>No of Days</th>
                <th>Reason</th>
            </tr>
            <%
                for (int i = 0; i <leavehistory.size(); i++) {
                    LeaveRequestBean leave = leavehistory.get(i);
            %>
                <tr>
                    <td><%= leave.getLeaveId() %></td>
                    <td><%= leave.getFromDate() %></td>
                    <td><%= leave.getToDate() %></td>
                    <td><%= leave.getNumDays() %></td>
                    <td><%= leave.getReason() %></td>
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
