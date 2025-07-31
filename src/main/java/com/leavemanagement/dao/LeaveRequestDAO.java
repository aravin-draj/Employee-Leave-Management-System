package com.leavemanagement.dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.leavemanagement.bean.LeaveRequestBean;
import com.leavemanagement.util.DBConnection;
import java.util.ArrayList;
import java.util.List;

public class LeaveRequestDAO {
	//Adding leave request into the database
    public boolean submitLeaveRequest(LeaveRequestBean leave) {
    	String sql = "INSERT INTO LeaveRequest (emp_id, from_date, to_date, num_days, reason, manager_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
        	Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, leave.getEmpId());
            pstmt.setDate(2, leave.getFromDate());
            pstmt.setDate(3, leave.getToDate());
            pstmt.setInt(4, leave.getNumDays());
            pstmt.setString(5, leave.getReason());
            pstmt.setInt(6, leave.getManagerId());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

       
    }
    //checking the user have a existing leave request or not
    public boolean hasPendingRequest(int empId) {
        String sql = "SELECT COUNT(*) FROM LeaveRequest WHERE emp_id = ? AND status = 'pending'";
        try {
        	Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, empId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //To change the status of the expired request which are not responded by the manager
    public void expireOldRequests() {
        String sql = "UPDATE LeaveRequest SET status = 'expired' WHERE from_date < CURRENT_DATE AND status = 'pending'";
        try {
        	Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //to get the most recent request of a employee
    public LeaveRequestBean getMostRecentRequestByEmpId(int empId) {
    	String sql = "SELECT * FROM (SELECT * FROM LeaveRequest WHERE emp_id = ? ORDER BY applied_on DESC) WHERE ROWNUM = 1";

        try  {
        	Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, empId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                LeaveRequestBean req = new LeaveRequestBean();
                req.setLeaveId(rs.getInt("leave_id"));
                req.setEmpId(rs.getInt("emp_id"));
                req.setFromDate(rs.getDate("from_date"));
                req.setToDate(rs.getDate("to_date"));
                req.setNumDays(rs.getInt("num_days"));
                req.setReason(rs.getString("reason"));
                req.setStatus(rs.getString("status"));
                req.setAppliedOn(rs.getDate("applied_on"));
                req.setDecisionDate(rs.getDate("decision_date"));
                req.setManagerId(rs.getInt("manager_id"));
                return req;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    //to get the leave history of the employee
    public ArrayList<LeaveRequestBean> getLeaveHistoryByEmpId(int empId,int managerId) {
        ArrayList<LeaveRequestBean> history = new ArrayList<>();
        String sql = "SELECT * FROM LeaveRequest WHERE emp_id = ? AND status = 'accepted' AND manager_id=? ORDER BY from_date DESC";


        try  {
        	Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, empId);
            pstmt.setInt(2, managerId);
            
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LeaveRequestBean req = new LeaveRequestBean();
                req.setLeaveId(rs.getInt("leave_id"));
                req.setEmpId(rs.getInt("emp_id"));
                req.setFromDate(rs.getDate("from_date"));
                req.setToDate(rs.getDate("to_date"));
                req.setNumDays(rs.getInt("num_days"));
                req.setReason(rs.getString("reason"));
                req.setStatus(rs.getString("status"));
                req.setAppliedOn(rs.getDate("applied_on"));
                req.setDecisionDate(rs.getDate("decision_date"));
                req.setManagerId(rs.getInt("manager_id"));
                history.add(req);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return history;
    }
    
    //to get all the pending request of the respective manager
    public ArrayList<LeaveRequestBean> getPendingRequestsForManager(int managerId) {
        ArrayList<LeaveRequestBean> requests = new ArrayList<>();

        String sql = "SELECT lr.* FROM LeaveRequest lr " +
                     "JOIN Employee e ON lr.emp_id = e.emp_id " +
                     "JOIN Department d ON e.dept_id = d.dept_id " +
                     "WHERE d.manager_id = ? AND lr.status = 'pending'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, managerId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LeaveRequestBean req = new LeaveRequestBean();
                req.setLeaveId(rs.getInt("leave_id"));
                req.setEmpId(rs.getInt("emp_id"));
                req.setFromDate(rs.getDate("from_date"));
                req.setToDate(rs.getDate("to_date"));
                req.setReason(rs.getString("reason"));
                req.setNumDays(rs.getInt("num_days"));
                req.setStatus(rs.getString("status"));
                req.setDecisionDate(rs.getDate("decision_date"));

                requests.add(req);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }
    
    //to set status and decision date on action of a manager to a leave request of a employee
    public boolean updateLeaveStatus(int leaveId, String status) {
        String sql = "UPDATE LeaveRequest SET status = ?, decision_date = SYSDATE WHERE leave_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, status);
            pstmt.setInt(2, leaveId);
            
            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //to get all the completed or expired request of the respective manager
    public ArrayList<LeaveRequestBean> getCompletedRequestsForManager(int managerId) {
        ArrayList<LeaveRequestBean> requests = new ArrayList<>();

        String sql = "SELECT lr.* FROM LeaveRequest lr " +
                     "JOIN Employee e ON lr.emp_id = e.emp_id " +
                     "JOIN Department d ON e.dept_id = d.dept_id " +
                     "WHERE d.manager_id = ? AND lr.status != 'pending'";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, managerId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LeaveRequestBean req = new LeaveRequestBean();
                req.setLeaveId(rs.getInt("leave_id"));
                req.setEmpId(rs.getInt("emp_id"));
                req.setFromDate(rs.getDate("from_date"));
                req.setToDate(rs.getDate("to_date"));
                req.setReason(rs.getString("reason"));
                req.setNumDays(rs.getInt("num_days"));
                req.setStatus(rs.getString("status"));
                req.setDecisionDate(rs.getDate("decision_date"));

                requests.add(req);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(requests.isEmpty()) {
        	return null;
        }
        else {
        return requests;
        }
    }




    
    


}
