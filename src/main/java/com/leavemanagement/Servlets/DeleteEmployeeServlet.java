package com.leavemanagement.Servlets;
import com.leavemanagement.dao.EmployeeDAO;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;


public class DeleteEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int empId = Integer.parseInt(request.getParameter("empId"));
        EmployeeDAO dao = new EmployeeDAO();

        boolean success = dao.deleteEmployee(empId);

        if (success) {
        	request.setAttribute("message", "Employee Deleted Successfully");
            request.setAttribute("redirectURL", "admindashboard.jsp");
            request.setAttribute("linkText", "Go to DashBoard");
            request.getRequestDispatcher("Message.jsp").forward(request, response);

        } else {
        	request.setAttribute("error", "Unable to Delete");
            request.setAttribute("redirectURL", "admindashboard.jsp");
            request.setAttribute("linkText","Go to DashBoard");
            request.getRequestDispatcher("error.jsp").forward(request, response);

        }
    }
}
