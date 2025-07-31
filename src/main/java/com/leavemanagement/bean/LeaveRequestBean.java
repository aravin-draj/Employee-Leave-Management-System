package com.leavemanagement.bean;

import java.io.Serializable;
import java.sql.Date;

public class LeaveRequestBean implements Serializable {

    private int leaveId;
    private int empId;
    private Date fromDate;
    private Date toDate;
    private int numDays;
    private String reason;
    private String status;
    private int managerId;
    private Date decisionDate;
    private Date appliedOn;

    // Default constructor
    public LeaveRequestBean() {
    }

    // Parameterized constructor (optional, for convenience)
    public LeaveRequestBean(int leaveId, int empId, Date fromDate, Date toDate, int numDays, String reason, String status, int managerId, Date decisionDate, Date appliedOn) {
        this.leaveId = leaveId;
        this.empId = empId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.numDays = numDays;
        this.reason = reason;
        this.status = status;
        this.managerId = managerId;
        this.decisionDate = decisionDate;
        this.appliedOn = appliedOn;
    }

    // Getters and Setters
    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        this.decisionDate = decisionDate;
    }

    public Date getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(Date appliedOn) {
        this.appliedOn = appliedOn;
    }
}
