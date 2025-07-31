package com.leavemanagement.Servlets;

import com.leavemanagement.bean.EmployeeBean;
import com.leavemanagement.dao.EmployeeDAO;

import javax.servlet.ServletException;

import javax.servlet.http.*;
import java.io.IOException;


public class EditEmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int empId = Integer.parseInt(request.getParameter("empId"));

        EmployeeDAO dao = new EmployeeDAO();
        EmployeeBean emp = dao.getEmployeeById(empId);

        if (emp != null) {
            request.setAttribute("employee", emp);
            request.getRequestDispatcher("UpdateEmployee.jsp").forward(request, response);
        } else {
        	request.setAttribute("error", "Employee Not Found");
            request.setAttribute("redirectURL", "admindashboard.jsp");
            request.setAttribute("linkText", "Go to Dashboard");
            request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}
}
