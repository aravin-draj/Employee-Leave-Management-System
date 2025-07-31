package com.leavemanagement.dao;
import com.leavemanagement.bean.EmployeeBean;
import java.util.*;
import com.leavemanagement.util.DBConnection;
import java.sql.*;

public class EmployeeDAO {

    private Connection conn;

    public boolean addEmployee(EmployeeBean emp) {
        String sql = "INSERT INTO Employee (name, email, password, role, dept_id) VALUES (?, ?, ?, ?, ?)";
        String check="SELECT * from Employee WHERE dept_id=? AND role=?";

        try{
        	Connection conn = DBConnection.getConnection();
        	if(emp.getRole()=="manager") {
        	PreparedStatement check_ps=conn.prepareStatement(check);
        	check_ps.setInt(1, emp.getDeptId());
        	check_ps.setString(2, emp.getRole());
        	ResultSet rs=check_ps.executeQuery();
        	if(rs.next()) {
        		return false;
        	}
        	}
        	PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, emp.getName());
            pstmt.setString(2, emp.getEmail());
            pstmt.setString(3, emp.getPassword());
            pstmt.setString(4, emp.getRole());
            pstmt.setInt(5, emp.getDeptId());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public EmployeeBean getEmployeeDetails(int emp_id) {
        String sql = "SELECT * FROM Employee WHERE emp_id = ?";
        try (Connection conn = DBConnection.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, emp_id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                EmployeeBean emp = new EmployeeBean();
                emp.setEmpId(rs.getInt("emp_id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("Email"));
                emp.setRole(rs.getString("role"));
                emp.setDeptId(rs.getInt("dept_id"));
                return emp;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public EmployeeBean EmployeevalidateLogin(String email, String password) {
        String sql = "SELECT * FROM Employee WHERE email = ? AND password = ? AND role= ?";
        try (Connection conn = DBConnection.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3,"employee");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                EmployeeBean emp = new EmployeeBean();
                emp.setEmpId(rs.getInt("emp_id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(email);
                emp.setRole(rs.getString("role"));
                emp.setDeptId(rs.getInt("dept_id"));
                return emp;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    //to get manager id for respective employee
    public int getManagerIdByEmployeeId(int empId) {
    	int managerId = -1;
        String sql = "SELECT d.manager_id " +
                     "FROM Employee e JOIN Department d ON e.dept_id = d.dept_id " +
                     "WHERE e.emp_id = ?";

        try  {
        	Connection conn = DBConnection.getConnection();
        	PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, empId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                managerId = rs.getInt("manager_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return managerId;
    }
    
    //to validate the manager login
    public EmployeeBean ManagervalidateLogin(String email, String password) {
        String sql = "SELECT * FROM Employee WHERE email = ? AND password = ? AND role= ?";
        try (Connection conn = DBConnection.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3,"manager");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                EmployeeBean emp = new EmployeeBean();
                emp.setEmpId(rs.getInt("emp_id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(email);
                emp.setRole(rs.getString("role"));
                emp.setDeptId(rs.getInt("dept_id"));
                return emp;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //to get the list of all employees
    public ArrayList<EmployeeBean> getAllEmployees() {
        ArrayList<EmployeeBean> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee ORDER BY dept_id, emp_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmployeeBean emp = new EmployeeBean();
                emp.setEmpId(rs.getInt("emp_id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setPassword(rs.getString("password"));
                emp.setDeptId(rs.getInt("dept_id"));
                emp.setRole(rs.getString("role"));
                list.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    //to update a employee details
    public boolean updateEmployee(EmployeeBean emp) {
        String sql = "UPDATE Employee SET name=?, email=?, password=?, dept_id=?, role=? WHERE emp_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, emp.getName());
            stmt.setString(2, emp.getEmail());
            stmt.setString(3, emp.getPassword());
            stmt.setInt(4, emp.getDeptId());
            stmt.setString(5, emp.getRole());
            stmt.setInt(6, emp.getEmpId());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //to delete a employee
    public boolean deleteEmployee(int empId) {
        String sql = "DELETE FROM Employee WHERE emp_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, empId);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //to get employee details as a bean by emp_id
    public EmployeeBean getEmployeeById(int empId) {
        String sql = "SELECT * FROM Employee WHERE emp_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                EmployeeBean emp = new EmployeeBean();
                emp.setEmpId(rs.getInt("emp_id"));
                emp.setName(rs.getString("name"));
                emp.setEmail(rs.getString("email"));
                emp.setPassword(rs.getString("password"));
                emp.setRole(rs.getString("role"));
                emp.setDeptId(rs.getInt("dept_id"));
                return emp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

	
	
