package com.altimetrik.bcp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class AttendanceByAccount extends AttendanceCommon{

	String account_name;	
	
	public AttendanceByAccount( String accountName, int total, int marked, int unMarked, int leaveCount, int leaveAppPend) {
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

	@Override
	public String toString() {
		return "Attendance [total=" + total + ", marked=" + marked + ", unmarked=" + unmarked + ", leave_count="
				+ leave_count + ", leave_app_pend=" + leave_app_pend + ", account_name=" + account_name + "]";
	}

}
