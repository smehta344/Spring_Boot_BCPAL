package com.altimetrik.bcp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Attendance {
	
	int total;
	
	int marked;
	
	int unmarked;
	
	int leave_count;
	
	int leave_app_pend;
	
	String account_name;
	
	

	public Attendance() {
	}

	public Attendance( String accountName, int total, int marked, int unMarked, int leaveCount, int leaveAppPend) {
		this.total = total;
		this.marked = marked;
		this.unmarked = unMarked;
		this.leave_count = leaveCount;
		this.leave_app_pend = leaveAppPend;
		this.account_name = accountName;
	}

	public String getAccountName() {
		return account_name;
	}

	public void setAccountName(String accountName) {
		this.account_name = accountName;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getMarked() {
		return marked;
	}

	public void setMarked(int marked) {
		this.marked = marked;
	}

	public int getUnMarked() {
		return unmarked;
	}

	public void setUnMarked(int unMarked) {
		this.unmarked = unMarked;
	}

	public int getLeaveCount() {
		return leave_count;
	}

	public void setLeaveCount(int leaveCount) {
		this.leave_count = leaveCount;
	}

	public int getLeaveAppPend() {
		return leave_app_pend;
	}

	public void setLeaveAppPend(int leaveAppPend) {
		this.leave_app_pend = leaveAppPend;
	}

	@Override
	public String toString() {
		return "Attendance [total=" + total + ", marked=" + marked + ", unmarked=" + unmarked + ", leave_count="
				+ leave_count + ", leave_app_pend=" + leave_app_pend + ", account_name=" + account_name + "]";
	}

}
