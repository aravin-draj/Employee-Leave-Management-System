package com.leavemanagement.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.leavemanagement.dao.EmployeeDAO;
import com.leavemanagement.dao.LeaveRequestDAO;
import com.leavemanagement.bean.EmployeeBean;
import com.leavemanagement.bean.LeaveRequestBean;

import java.util.ArrayList;
import java.util.List;

public class ViewLeaveHistoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(false);
        if(session==null) {
        	response.sendRedirect("index.jsp");
        }
        EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
        Integer deptId =employee.getDeptId();
        Integer emp_id=Integer.parseInt(request.getParameter("empId"));
        
        LeaveRequestDAO dao = new LeaveRequestDAO();
        EmployeeDAO emp_dao=new EmployeeDAO();
        ArrayList<LeaveRequestBean> history = dao.getLeaveHistoryByEmpId(emp_id,deptId);
        EmployeeBean emp_details=emp_dao.getEmployeeDetails(emp_id);
        
        if(!history.isEmpty()) {
        	request.setAttribute("leavehistory", history);
        	request.setAttribute("emp_details", emp_details);
            request.getRequestDispatcher("ViewLeaveHistory.jsp").forward(request, response);
        }
        else {
        	request.setAttribute("message", "No LeaveHistory or Employee Not Found");
            request.setAttribute("redirectURL", "ManagerDashboard.jsp");
            request.setAttribute("linkText", "Go to Dashboard");
            request.getRequestDispatcher("Message.jsp").forward(request, response);
        }

    }
}
