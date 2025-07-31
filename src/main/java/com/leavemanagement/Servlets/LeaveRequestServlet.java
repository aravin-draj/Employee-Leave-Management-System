package com.leavemanagement.Servlets;

import com.leavemanagement.bean.EmployeeBean;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.leavemanagement.bean.LeaveRequestBean;
import com.leavemanagement.dao.*;
import java.time.*;

public class LeaveRequestServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get form data
		String fromDateStr = request.getParameter("fromDate");
        String toDateStr = request.getParameter("toDate");
        String reason = request.getParameter("reason");
        
        if (fromDateStr == null || toDateStr == null || fromDateStr.isEmpty() || toDateStr.isEmpty()) {
            request.setAttribute("error", "From Date or To Date cannot be empty.");
            request.setAttribute("redirectURL", "leaveRequest.jsp");
            request.setAttribute("linkText", "Go back to Leave Request Form");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        
        //getting emp data from current session
        HttpSession session = request.getSession(false);
        if(session==null) {
        	response.sendRedirect("index.jsp");
        }
        EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
        Integer empId =employee.getEmpId();

        
        // getting dates and validation
        LocalDate fromDate = LocalDate.parse(fromDateStr);
        LocalDate toDate = LocalDate.parse(toDateStr);
        LocalDate today = LocalDate.now();
        
        if (fromDate.isBefore(today)) {
            request.setAttribute("error", "From date cannot be in the past.");
            request.setAttribute("redirectURL", "leaveRequest.jsp");
            request.setAttribute("linkText", "Request Again");
            request.getRequestDispatcher("error.jsp").forward(request, response);            
            return;
        }

        if (fromDate.isAfter(toDate)) {
            request.setAttribute("error", "From date cannot be after To date.");
            request.setAttribute("redirectURL", "leaveRequest.jsp");
            request.setAttribute("linkText", "Request Again");
            request.getRequestDispatcher("error.jsp").forward(request, response);            
            return;
        }
        
        //counting no of days leave excluding sat and sun
        int numDays = 0;
        LocalDate tempDate = fromDate;

        while (!tempDate.isAfter(toDate)) {
            DayOfWeek day = tempDate.getDayOfWeek();
            if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) {
                numDays++;
            }
            tempDate = tempDate.plusDays(1);
        }
        
        // dao object
        LeaveRequestDAO dao=new LeaveRequestDAO();
        EmployeeDAO emp_dao=new EmployeeDAO();
        
        //getting the manager id for the respective employee
        int managerId = emp_dao.getManagerIdByEmployeeId(empId);
        
        //checking for existing request
        if (dao.hasPendingRequest(empId)) {
            request.setAttribute("error", "You already have a pending leave request.");
            request.setAttribute("redirectURL", "EmployeeDashboard.jsp");
            request.setAttribute("linkText", "Go to DashBoard");
            request.getRequestDispatcher("error.jsp").forward(request, response);            
            return;
        }
        
        //creating bean
        LeaveRequestBean leave = new LeaveRequestBean();
        leave.setEmpId(empId);
        leave.setFromDate(java.sql.Date.valueOf(fromDate));
        leave.setToDate(java.sql.Date.valueOf(toDate));
        leave.setReason(reason);
        leave.setStatus("Pending");
        leave.setManagerId(managerId);
        leave.setNumDays(numDays);

        boolean success = dao.submitLeaveRequest(leave);
        if (success) {
            request.setAttribute("message", "Leave request submitted successfully.");
            request.setAttribute("redirectURL", "viewStatus.jsp");
            request.setAttribute("linkText", "View Status");
            request.getRequestDispatcher("Message.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Failed to submit leave request. Please try again.");
            request.setAttribute("redirectURL", "leaveRequest.jsp");
            request.setAttribute("linkText", "Request Again");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

	}

}
