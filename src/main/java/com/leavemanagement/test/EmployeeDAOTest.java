package com.leavemanagement.test;

import com.leavemanagement.dao.EmployeeDAO;
import com.leavemanagement.bean.EmployeeBean;

public class EmployeeDAOTest {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();

        // Replace with actual credentials in your DB
        String testEmail = "charlie@company.com";
        String testPassword = "pass123";

        EmployeeBean emp = dao.EmployeevalidateLogin(testEmail, testPassword);

        if (emp != null) {
            System.out.println("✅ Login successful!");
            System.out.println("ID: " + emp.getEmpId());
            System.out.println("Name: " + emp.getName());
            System.out.println("Email: " + emp.getEmail());
            System.out.println("Role: " + emp.getRole());
            System.out.println("Dept ID: " + emp.getDeptId());
        } else {
            System.out.println("❌ Login failed or no employee found.");
        }
    }
}
