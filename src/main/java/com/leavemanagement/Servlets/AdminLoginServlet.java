package com.leavemanagement.Servlets;

import com.leavemanagement.dao.AdminDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("email");
        String password = request.getParameter("password");

        AdminDAO dao = new AdminDAO();
        boolean isValid = dao.validateAdminLogin(username, password);

        if (isValid) {
            HttpSession session = request.getSession();
            session.setAttribute("adminUsername", username);
            response.sendRedirect("admindashboard.jsp");
        } else {
        	request.setAttribute("error", "Invalid username or password");
            request.setAttribute("redirectURL", "login.jsp?role=admin");
            request.setAttribute("linkText", "Login Again");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        }
    }
