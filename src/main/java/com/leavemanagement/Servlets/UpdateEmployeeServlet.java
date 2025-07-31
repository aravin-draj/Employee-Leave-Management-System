package com.leavemanagement.Servlets;

import com.leavemanagement.bean.EmployeeBean;
import com.leavemanagement.dao.EmployeeDAO;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int empId = Integer.parseInt(request.getParameter("empId"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        int deptId = Integer.parseInt(request.getParameter("deptId"));

        EmployeeBean emp = new EmployeeBean();
        emp.setEmpId(empId);
        emp.setName(name);
        emp.setEmail(email);
        emp.setPassword(password);
        emp.setRole(role);
        emp.setDeptId(deptId);

        EmployeeDAO dao = new EmployeeDAO();
        boolean success = dao.updateEmployee(emp);

        if (success) {
        	request.setAttribute("message", "Updated Succesfully");
            request.setAttribute("redirectURL", "admindashboard.jsp");
            request.setAttribute("linkText", "Go to DashBoard");
            request.getRequestDispatcher("Message.jsp").forward(request, response);

        } else {
        	request.setAttribute("error", "Unable to Update");
            request.setAttribute("redirectURL", "editemployee.jsp");
            request.setAttribute("linkText", "Try Again");
            request.getRequestDispatcher("error.jsp").forward(request, response);

        }
    }
}
