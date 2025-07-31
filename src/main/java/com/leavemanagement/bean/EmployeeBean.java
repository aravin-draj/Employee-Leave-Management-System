package com.leavemanagement.bean;

import java.io.Serializable;

public class EmployeeBean implements Serializable {

    private int empId;
    private String name;
    private String email;
    private String password;
    private String role;   
    private int deptId;

 // Default constructor
    public EmployeeBean() { }

    // Parameterized constructor
    public EmployeeBean(int empId, String name, String email, String password, String role, int deptId) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.deptId = deptId;
    }
    
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

}
