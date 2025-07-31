<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.leavemanagement.bean.LeaveRequestBean,com.leavemanagement.bean.EmployeeBean " %>
<!DOCTYPE html>
<html>
<head>
    <title>Pending Leave Requests</title>
</head>
<body>
    <form action="UpdateLeaveStatus" method="post">
    <h1>Pending Leave Requests <h1><input type="submit" value="Submit Decisions"> </h1> </h1>
    
    
        <%
            List<LeaveRequestBean> pendingRequests = (List<LeaveRequestBean>) request.getAttribute("pendingRequests");
        	List<EmployeeBean> emp_details = (List<EmployeeBean>) request.getAttribute("emp_details");
                for (int i=0;i<pendingRequests.size();i++) {
        %>
                    <fieldset>
                    	<p>Employee Name: <%= emp_details.get(i).getName() %></p>
                    	<p>Email: <%= emp_details.get(i).getEmail() %></p>
                        <p>Employee ID: <%= pendingRequests.get(i).getEmpId() %></p>
                        <p>Leave ID: <%= pendingRequests.get(i).getLeaveId() %></p>
                        <p>From Date: <%= pendingRequests.get(i).getFromDate() %></p>
                        <p>To Date: <%= pendingRequests.get(i).getToDate() %></p>
                        <p>No of Days: <%= pendingRequests.get(i).getNumDays() %></p>
                        <p>Reason: <%= pendingRequests.get(i).getReason() %></p>

                        <label>
                            <input type="radio" name="action_<%= pendingRequests.get(i).getLeaveId() %>" value="accept">
                            Accept
                        </label>
                        <label>
                            <input type="radio" name="action_<%= pendingRequests.get(i).getLeaveId() %>" value="reject">
                            Reject
                        </label>
                        <input type="hidden" name="leaveIds" value="<%= pendingRequests.get(i).getLeaveId() %>">
                    </fieldset>
                    <hr>
                     <br>
     <% } %>   
       
    </form>
</body>
</html>
