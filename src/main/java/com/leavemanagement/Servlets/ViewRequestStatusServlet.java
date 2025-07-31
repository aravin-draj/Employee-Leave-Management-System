package com.leavemanagement.Servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.leavemanagement.dao.LeaveRequestDAO;
import com.leavemanagement.bean.EmployeeBean;
import com.leavemanagement.bean.LeaveRequestBean;
public class ViewRequestStatusServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        if(session==null) {
        	response.sendRedirect("index.jsp");
        }
        EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
        int empId =employee.getEmpId();
        
        LeaveRequestDAO dao = new LeaveRequestDAO();
        LeaveRequestBean leaveRequest = dao.getMostRecentRequestByEmpId(empId);

        if (leaveRequest != null) {
            request.setAttribute("leaveRequest", leaveRequest);
            request.getRequestDispatcher("viewrequeststatus.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "You have not submitted any leave requests.");
            request.setAttribute("redirectURL", "EmployeeDashboard.jsp");
            request.setAttribute("linkText", "Go to DashBoard");
            request.getRequestDispatcher("Message.jsp").forward(request, response);
        }
    }
}
