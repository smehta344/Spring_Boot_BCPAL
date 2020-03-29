package com.altimetrik.bcp.entity;


public class Attendance {
	
	int total;
	
	int marked;
	
	int unMarked;
	
	int leaveCount;
	
	int leaveAppPend;
	
	String accountName;

	public String getAccountName() {
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

	public int getUnMarked() {
		return unMarked;
	}

	public void setUnMarked(int unMarked) {
		this.unMarked = unMarked;
	}

	public int getLeaveCount() {
		return leaveCount;
	}

	public void setLeaveCount(int leaveCount) {
		this.leaveCount = leaveCount;
	}

	public int getLeaveAppPend() {
		return leaveAppPend;
	}

	public void setLeaveAppPend(int leaveAppPend) {
		this.leaveAppPend = leaveAppPend;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Attendance [id=");
		builder.append(total);
		builder.append(", marked=");
		builder.append(marked);
		return builder.toString();
	}

}
