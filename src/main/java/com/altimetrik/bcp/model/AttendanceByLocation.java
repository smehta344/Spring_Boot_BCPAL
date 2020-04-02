package com.altimetrik.bcp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class AttendanceByLocation extends AttendanceCommon{
	
	String client_location;


	public String getClient_location() {
		return client_location;
	}

	public void setClient_location(String client_location) {
		this.client_location = client_location;
	}

	public AttendanceByLocation( String accountName, int total, int marked, int unMarked, int leaveCount/*, int leaveAppPend*/) {
		this.total = total;
		this.marked = marked;
		this.unmarked = unMarked;
		this.leave_count = leaveCount;
		//this.leave_app_pend = leaveAppPend;
		this.client_location = accountName;
	}

	@Override
	public String toString() {
		return "Attendance [total=" + total + ", marked=" + marked + ", unmarked=" + unmarked + ", leave_count="
				+ leave_count + ", leave_app_pend=" + leave_app_pend + ", client_location=" + client_location + "]";
	}

}
