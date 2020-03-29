package com.altimetrik.bcp.util;

public class MysqlQueryConstants {

	public static final String ATTENDANCE_COUNT_QUERY="select account_name,count(*) total,"+
			"count(case when attendance_status='Marked' then 1 end) marked,"+ 
			"count(case when attendance_status='Not Marked' then 1 end) unmarked," +
			"count(case when attendance_status='Leave' then 1 end) leave_count," +
			"count(case when attendance_status='Leave - Approval Pending' then 1 end) leave_app_pend" + " " + 
			"from attendance_status st" + " " + 
			"where" + " " +
			"attendance_date= :startDate"+ " " +
			"group by account_name;";
}
