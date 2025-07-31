package com.leavemanagement.Servlets;

import com.leavemanagement.bean.EmployeeBean;
import com.leavemanagement.dao.EmployeeDAO;


import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;


public class ViewAllEmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EmployeeDAO dao = new EmployeeDAO();
        ArrayList<EmployeeBean> employeeList = dao.getAllEmployees();

        if (!employeeList.isEmpty()) {
            request.setAttribute("employeelist",employeeList);
            request.getRequestDispatcher("viewallemployees.jsp").forward(request, response);
        } else {
        	request.setAttribute("message", "No Employees found");
            request.setAttribute("redirectURL", "admindashboard.jsp");
            request.setAttribute("linkText", "Go to Dashboard");
            request.getRequestDispatcher("Message.jsp").forward(request, response);
    }
    }
}
