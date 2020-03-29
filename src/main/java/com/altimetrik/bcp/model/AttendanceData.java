package com.altimetrik.bcp.model;

public class AttendanceData {
	int total;
	int marked;
	int unmarked;
	int leave;
	int marked_percent;
	int unmarked_percent;
	int leave_percent;
	int accountId;
	String accountName;
	public String getAccountName() {
		if(accountName != null)
			return accountName.toUpperCase();
		else
			return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
	public int getUnmarked() {
		return unmarked;
	}
	public void setUnmarked(int unmarked) {
		this.unmarked = unmarked;
	}
	public int getLeave() {
		return leave;
	}
	public void setLeave(int leave) {
		this.leave = leave;
	}
	public int getMarked_percent() {
		return marked_percent;
	}
	public void setMarked_percent(int marked_percent) {
		this.marked_percent = marked_percent;
	}
	public int getUnmarked_percent() {
		return unmarked_percent;
	}
	public void setUnmarked_percent(int unmarked_percent) {
		this.unmarked_percent = unmarked_percent;
	}
	public int getLeave_percent() {
		return leave_percent;
	}
	public void setLeave_percent(int leave_percent) {
		this.leave_percent = leave_percent;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

}
