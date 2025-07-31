package com.leavemanagement.Servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.leavemanagement.dao.LeaveRequestDAO;
import com.leavemanagement.dao.EmployeeDAO;
import com.leavemanagement.bean.EmployeeBean;
import com.leavemanagement.bean.LeaveRequestBean;
import java.util.ArrayList;

public class ViewPendingRequestsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        if(session==null) {
        	response.sendRedirect("index.jsp");
        }
        EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
        Integer managerId =employee.getEmpId();
        
        LeaveRequestDAO dao = new LeaveRequestDAO();
        EmployeeDAO emp_dao=new EmployeeDAO();
        ArrayList<LeaveRequestBean> pendingRequests = dao.getPendingRequestsForManager(managerId);
        ArrayList<EmployeeBean> emp_details=new ArrayList<>();
        for(LeaveRequestBean req : pendingRequests) {
        	emp_details.add(emp_dao.getEmployeeDetails(req.getEmpId()));
        }
        
        if(!pendingRequests.isEmpty()) {
        	request.setAttribute("pendingRequests", pendingRequests);
        	request.setAttribute("emp_details", emp_details);
            request.getRequestDispatcher("ViewPendingRequest.jsp").forward(request, response);
        }
        else {
        	request.setAttribute("message", "No Pending Request");
            request.setAttribute("redirectURL", "ManagerDashboard.jsp");
            request.setAttribute("linkText", "Go to Dashboard");
            request.getRequestDispatcher("Message.jsp").forward(request, response);
        }
        
    }
}
