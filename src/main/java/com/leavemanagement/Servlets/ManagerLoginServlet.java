package com.leavemanagement.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.leavemanagement.dao.EmployeeDAO;
import com.leavemanagement.bean.EmployeeBean;


public class ManagerLoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		EmployeeDAO emp_dao=new EmployeeDAO();
		EmployeeBean emp=emp_dao.ManagervalidateLogin(email,password);
		
		if (emp != null) {
            HttpSession session = request.getSession();
            session.setAttribute("employee", emp);
            response.sendRedirect("ManagerDashboard.jsp");
	}
		else {
			request.setAttribute("error", "Invalid email or password");
            request.setAttribute("redirectURL", "login.jsp?role=manager");
            request.setAttribute("linkText", "Login Again");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
	}
}
