<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.leavemanagement.bean.EmployeeBean" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Employees</title>
</head>
<body>
    <h1>All Employee Details</h1>

    <%
        ArrayList<EmployeeBean> employeeList = (ArrayList<EmployeeBean>) request.getAttribute("employeelist");

    %>
            <table border="1" cellpadding="5" cellspacing="0">
                <tr>
                    <th>Employee ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Role</th>
                    <th>Department ID</th>
                </tr>

                <%
                    for (EmployeeBean emp : employeeList) {
                %>
                    <tr>
                        <td><%= emp.getEmpId() %></td>
                        <td><%= emp.getName() %></td>
                        <td><%= emp.getEmail() %></td>
                        <td><%= emp.getPassword() %></td>
                        <td><%= emp.getRole() %></td>
                        <td><%= emp.getDeptId() %></td>
                    </tr>
                <%
                    }
                %>
            </table>

</body>
</html>
