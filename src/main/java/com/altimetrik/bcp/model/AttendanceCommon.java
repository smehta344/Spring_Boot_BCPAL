package com.altimetrik.bcp.model;

public class AttendanceCommon {
	
	int total;
	
	int marked;
	
	int unmarked;
	
	int leave_count;
	
	int leave_app_pend;
		
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

	public int getLeave_count() {
		return leave_count;
	}

	public void setLeave_count(int leave_count) {
		this.leave_count = leave_count;
	}

	public int getLeave_app_pend() {
		return leave_app_pend;
	}

	public void setLeave_app_pend(int leave_app_pend) {
		this.leave_app_pend = leave_app_pend;
	}

}
