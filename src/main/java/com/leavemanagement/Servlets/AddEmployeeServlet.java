package com.leavemanagement.Servlets;

import com.leavemanagement.bean.EmployeeBean;
import com.leavemanagement.dao.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AddEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        int deptId = Integer.parseInt(request.getParameter("deptId"));
        String role = request.getParameter("role");

        // Set bean
        EmployeeBean emp = new EmployeeBean();
        emp.setName(name);
        emp.setEmail(email);
        emp.setPassword(password);
        emp.setDeptId(deptId);
        emp.setRole(role);

        // DAO call
        EmployeeDAO dao = new EmployeeDAO();
        boolean success = dao.addEmployee(emp);

        // Redirect
        if (success) {
        	request.setAttribute("message", "Employee Added Successfully");
            request.setAttribute("redirectURL", "admindashboard.jsp");
            request.setAttribute("linkText", "Go to DashBoard");
            request.getRequestDispatcher("Message.jsp").forward(request, response);

        } else {
        	request.setAttribute("error", "some issue or invalid employee add");
            request.setAttribute("redirectURL", "admindashboard.jsp");
            request.setAttribute("linkText","Go to DashBoard");
            request.getRequestDispatcher("error.jsp").forward(request, response);

        }
    }
}
