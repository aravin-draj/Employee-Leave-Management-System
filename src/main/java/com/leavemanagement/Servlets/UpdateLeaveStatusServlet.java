package com.leavemanagement.Servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import com.leavemanagement.dao.LeaveRequestDAO;

public class UpdateLeaveStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get all leave IDs from hidden inputs
        String[] leaveIds = request.getParameterValues("leaveIds");

        LeaveRequestDAO dao = new LeaveRequestDAO();
        boolean allSuccessful = true;

        if (leaveIds != null) {
            for (String leaveIdStr : leaveIds) {
                try {
                    int leaveId = Integer.parseInt(leaveIdStr);
                    String status="pending";
                    String action = request.getParameter("action_" + leaveId); // "accept" or "reject"
                    
                    
                    if(action!=null) {
                    	if(action.equals("accept")){
                        	status="accepted";
                        }
                        else if(action.equals("reject")) {
                        	status="rejected";
                        }
                    }	

                    boolean updated = dao.updateLeaveStatus(leaveId, status);
                    if(!updated) {
                    	allSuccessful=false;
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (allSuccessful) {
            response.sendRedirect("ManagerDashboard.jsp");
        } else {
            request.setAttribute("error", "Some statuses may not have been updated properly.");
            request.setAttribute("redirectURL", "ManagerDashboard.jsp");
            request.setAttribute("linkText", "Go to Dashboard");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
