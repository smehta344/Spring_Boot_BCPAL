package com.altimetrik.bcp.model;

import java.util.List;

import com.altimetrik.bcp.entity.AttendanceStatus;

public class AttendanceData {
	double total;
	double marked;
	double unmarked;
	double leave;
	double leaveAppPending;
	double marked_percent;
	double unmarked_percent;
	double leave_percent;
	double leave_app_pend_percent;
	int accountId;
	String accountName;
	String locationName;
	List<AttendanceStatus> employeeDetails;
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getAccountName() {
		if(accountName != null)
			return accountName.toUpperCase();
		else
			return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getMarked() {
		return marked;
	}
	public void setMarked(double marked) {
		this.marked = marked;
	}
	public double getUnmarked() {
		return unmarked;
	}
	public void setUnmarked(double unmarked) {
		this.unmarked = unmarked;
	}
	public double getLeave() {
		return leave;
	}
	public void setLeave(double leave) {
		this.leave = leave;
	}
	public double getMarked_percent() {
		return marked_percent;
	}
	public void setMarked_percent(double marked_percent) {
		this.marked_percent = marked_percent;
	}
	public double getUnmarked_percent() {
		return unmarked_percent;
	}
	public void setUnmarked_percent(double unmarked_percent) {
		this.unmarked_percent = unmarked_percent;
	}
	public double getLeave_percent() {
		return leave_percent;
	}
	public void setLeave_percent(double leave_percent) {
		this.leave_percent = leave_percent;
	}
	
	public double getLeave_app_pend_percent() {
		return leave_app_pend_percent;
	}
	public void setLeave_app_pend_percent(double leave_app_pend_percent) {
		this.leave_app_pend_percent = leave_app_pend_percent;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
	public double getLeaveAppPending() {
		return leaveAppPending;
	}
	public void setLeaveAppPending(double leaveAppPending) {
		this.leaveAppPending = leaveAppPending;
	}
	public List<AttendanceStatus> getEmployeeDetails() {
		return employeeDetails;
	}
	public void setEmployeeDetails(List<AttendanceStatus> employeeDetails) {
		this.employeeDetails = employeeDetails;
	}
	@Override
	public String toString() {
		return "AttendanceData [total=" + total + ", marked=" + marked + ", unmarked=" + unmarked + ", leave=" + leave
				+ ", marked_percent=" + marked_percent + ", unmarked_percent=" + unmarked_percent + ", leave_percent="
				+ leave_percent + ", accountId=" + accountId + ", accountName=" + accountName + ", locationName="
				+ locationName + ", employeeDetails=" + employeeDetails + "]";
	}
	
	
}
