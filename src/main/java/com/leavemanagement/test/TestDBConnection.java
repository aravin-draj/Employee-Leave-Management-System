package com.leavemanagement.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.leavemanagement.bean.EmployeeBean;
import com.leavemanagement.util.DBConnection;

public class TestDBConnection {
    public static void main(String[] args) {
        try(Connection conn = DBConnection.getConnection()){
        if (conn != null) {
            System.out.println("✅ DB connection successful!");
        } else {
            System.out.println("❌ DB connection failed.");
        }
        String sql = "SELECT * FROM Employee WHERE email = ? AND password = ? AND role= ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "charlie@company.com");
        pstmt.setString(2, "pass123");
        pstmt.setString(3,"employee");
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
        	System.out.println(rs.getInt("emp_id"));
        	System.out.println(rs.getString("name"));
        	System.out.println(rs.getString("role"));
        	System.out.println(rs.getInt("dept_id"));
        } else {
            System.out.println("Not found");
        }

    }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}