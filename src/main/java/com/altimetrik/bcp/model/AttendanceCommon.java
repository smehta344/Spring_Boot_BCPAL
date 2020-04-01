package com.altimetrik.bcp.model;

public class AttendanceCommon {
	
	double total;
	
	double marked;
	
	double unmarked;
	
	double leave_count;
	
	double leave_app_pend;
		
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

	public double getLeave_count() {
		return leave_count;
	}

	public void setLeave_count(double leave_count) {
		this.leave_count = leave_count;
	}

	public double getLeave_app_pend() {
		return leave_app_pend;
	}

	public void setLeave_app_pend(double leave_app_pend) {
		this.leave_app_pend = leave_app_pend;
	}

}
