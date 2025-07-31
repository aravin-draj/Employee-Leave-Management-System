package com.leavemanagement.dao;

import java.sql.*;
import com.leavemanagement.util.DBConnection;

public class AdminDAO {

    public boolean validateAdminLogin(String username, String password) {
        boolean isValid = false;

        String sql = "SELECT * FROM AdminUser WHERE username= ? AND password = ?";

        try{
        	Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                isValid = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }
}