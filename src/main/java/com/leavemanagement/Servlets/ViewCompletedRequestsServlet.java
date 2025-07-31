package com.leavemanagement.Servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.leavemanagement.dao.LeaveRequestDAO;
import com.leavemanagement.dao.EmployeeDAO;
import com.leavemanagement.bean.EmployeeBean;
import com.leavemanagement.bean.LeaveRequestBean;
import java.util.ArrayList;

public class ViewCompletedRequestsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
        Integer managerId =employee.getEmpId();

       
        LeaveRequestDAO dao = new LeaveRequestDAO();
        EmployeeDAO emp_dao=new EmployeeDAO();
        ArrayList<LeaveRequestBean> completedrequests = dao.getCompletedRequestsForManager(managerId);
        ArrayList<EmployeeBean> emp_details=new ArrayList<>();
        for(LeaveRequestBean req : completedrequests) {
        	emp_details.add(emp_dao.getEmployeeDetails(req.getEmpId()));
        }
        if(!completedrequests.isEmpty()) {
        	request.setAttribute("completedrequests", completedrequests);
        	request.setAttribute("emp_details", emp_details);
            request.getRequestDispatcher("CompledtedRequests.jsp").forward(request, response);
        	
        }
        else {
        	request.setAttribute("message", "No Completed Request");
            request.setAttribute("redirectURL", "ManagerDashboard.jsp");
            request.setAttribute("linkText", "Go to Dashboard");
            request.getRequestDispatcher("Message.jsp").forward(request, response);
        }

        
    }
}
