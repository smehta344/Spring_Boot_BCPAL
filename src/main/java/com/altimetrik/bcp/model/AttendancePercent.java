package com.altimetrik.bcp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttendancePercent {
	
	@JsonProperty("marked_percent")
	private int marked_percent;
	
	@JsonProperty("unmarked_percent")
	private int unmarked_percent;
	
	@JsonProperty("leave_percent")
	private int leave_percent;
	
	@JsonProperty("leave_app_pend_percent")
	private int leave_app_pend_percent;
	
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
	public int getLeave_app_pend_percent() {
		return leave_app_pend_percent;
	}
	public void setLeave_app_pend_percent(int leave_app_pend_percent) {
		this.leave_app_pend_percent = leave_app_pend_percent;
	}
	@Override
	public String toString() {
		return "AttendancePercent [marked_percent=" + marked_percent + ", unmarked_percent=" + unmarked_percent
				+ ", leave_percent=" + leave_percent + ", leave_app_pend_percent=" + leave_app_pend_percent + "]";
	}
}
