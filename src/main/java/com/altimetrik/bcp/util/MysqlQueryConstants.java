package com.altimetrik.bcp.util;

public class MysqlQueryConstants {
	
	private MysqlQueryConstants(){}

	public static final String ATTENDANCE_COUNT_QUERY="select account_name,count(*) total,"+
			"count(case when attendance_status='Marked' then 1 end) marked,"+ 
			"count(case when attendance_status='Not Marked' then 1 end) unmarked," +
			"count(case when attendance_status IN ('Leave','Leave - Approval Pending','Floater Holiday - Approval Pending','Floater Holiday') then 1 end) leave_count  " +
			//"count(case when attendance_status='Leave - Approval Pending' then 1 end) leave_app_pend" + " " + 
			"from attendance_status st" + " " + 
			"where" + " " +
			"attendance_date= :startDate"+ " " +
			"group by account_name;";
	public static final String GET_ATTENDANCE_COUNT_BY_ACC_NAME="select account_name,count(*) total,"+
			"count(case when attendance_status='Marked' then 1 end) marked,"+ 
			"count(case when attendance_status='Not Marked' then 1 end) unmarked," +
			"count(case when attendance_status IN ('Leave','Leave - Approval Pending','Floater Holiday - Approval Pending','Floater Holiday') then 1 end) leave_count  " +
			//"count(case when attendance_status='Leave - Approval Pending' then 1 end) leave_app_pend" + " " + 
			"from attendance_status st" + " " + 
			"where" + " " +
			"account_name= :accountName"+" AND " +
			"attendance_date= :startDate ;";
	public static final String GET_ATTENDANCE_COUNT_ATTD_STATUS_AND_DATE="select account_name,count(*) total,"+
			"count(case when attendance_status='Marked' then 1 end) marked,"+ 
			"count(case when attendance_status='Not Marked' then 1 end) unmarked," +
			"count(case when attendance_status IN ('Leave','Leave - Approval Pending','Floater Holiday - Approval Pending','Floater Holiday') then 1 end) leave_count  " +
			//"count(case when attendance_status='Leave - Approval Pending' then 1 end) leave_app_pend" + " " + 
			"from attendance_status st" + " " + 
			"where" + " " +
			//"attendance_status= :attendanceStatus "+" AND " +
			"attendance_date= :startDate" +" "+
			"group by account_name;";
	
	public static final String ATTENDANCE_LOCATION_QUERY="select client_location,count(*) total,"+
			"count(case when attendance_status='Marked' then 1 end) marked,"+ 
			"count(case when attendance_status='Not Marked' then 1 end) unmarked," +
			"count(case when attendance_status IN ('Leave','Leave - Approval Pending','Floater Holiday - Approval Pending','Floater Holiday') then 1 end) leave_count  " +
			//"count(case when attendance_status='Leave - Approval Pending' then 1 end) leave_app_pend" + " " + 
			"from attendance_status st" + " " + 
			"where" + " " +
			"attendance_date= :startDate"+ " " +
			"group by client_location;";
	public static final String GET_ATTENDANCE_COUNT_BY_LOCATION="select client_location,count(*) total,"+
			"count(case when attendance_status='Marked' then 1 end) marked,"+ 
			"count(case when attendance_status='Not Marked' then 1 end) unmarked," +
			"count(case when attendance_status IN ('Leave','Leave - Approval Pending','Floater Holiday - Approval Pending','Floater Holiday') then 1 end) leave_count  " +
			//"count(case when attendance_status='Leave - Approval Pending' then 1 end) leave_app_pend" + " " + 
			"from attendance_status st" + " " + 
			"where" + " " +
			"client_location= :clinetLocation"+" AND " +
			"attendance_date= :startDate ;";
	public static final String GET_ATTENDANCE_LOCATION_COUNT_ATTD_STATUS_AND_DATE="select client_location,count(*) total,"+
			"count(case when attendance_status='Marked' then 1 end) marked,"+ 
			"count(case when attendance_status='Not Marked' then 1 end) unmarked," +
			"count(case when attendance_status IN ('Leave','Leave - Approval Pending','Floater Holiday - Approval Pending','Floater Holiday') then 1 end) leave_count  " +
			//"count(case when attendance_status='Leave - Approval Pending' then 1 end) leave_app_pend" + " " + 
			"from attendance_status st" + " " + 
			"where" + " " +
			//"attendance_status= :attendanceStatus "+" AND " +
			"attendance_date= :startDate" +" "+
			"group by client_location;";
	public static final String GET_ATTENDANCE_COUNT_CATEGORY_AND_DATE="select account_name,count(*) total,"+
			"count(case when attendance_status='Marked' then 1 end) marked,"+ 
			"count(case when attendance_status='Not Marked' then 1 end) unmarked," +
			"count(case when attendance_status IN ('Leave','Leave - Approval Pending','Floater Holiday - Approval Pending','Floater Holiday') then 1 end) leave_count  " +
			//"count(case when attendance_status='Leave - Approval Pending' then 1 end) leave_app_pend" + " " + 
			"from attendance_status st" + " " + 
			"where" + " " +
			"category= :category "+" AND " +
			"attendance_date= :startDate"+ " " +
			"group by account_name;";
	public static final String GET_ATTENDANCE_COUNT_BY_ACC_NAME_AND_CATEGORY_DATE="select account_name,count(*) total,"+
			"count(case when attendance_status='Marked' then 1 end) marked,"+ 
			"count(case when attendance_status='Not Marked' then 1 end) unmarked," +
			"count(case when attendance_status IN ('Leave','Leave - Approval Pending','Floater Holiday - Approval Pending','Floater Holiday') then 1 end) leave_count  " +
			//"count(case when attendance_status='Leave - Approval Pending' then 1 end) leave_app_pend" + " " + 
			"from attendance_status st" + " " + 
			"where" + " " +
			"account_name= :accountName"+" AND " +
			"category= :category"+" AND " +
			"attendance_date= :startDate ;";
	public static final String GET_ATTENDANCE_COUNT_BY_LOCATION_AND_CATEGORY_DATE="select client_location,count(*) total,"+
			"count(case when attendance_status='Marked' then 1 end) marked,"+ 
			"count(case when attendance_status='Not Marked' then 1 end) unmarked," +
			"count(case when attendance_status IN ('Leave','Leave - Approval Pending','Floater Holiday - Approval Pending','Floater Holiday') then 1 end) leave_count  " +
			//"count(case when attendance_status='Leave - Approval Pending' then 1 end) leave_app_pend" + " " + 
			"from attendance_status st" + " " + 
			"where" + " " +
			"category= :category"+" AND " +
			"attendance_date= :startDate"+ " " +
			"group by client_location;";
	public static final String GET_ATTENDANCE_COUNT_BY_PARTICULAR_LOCATION_AND_CATEGORY_DATE="select client_location,count(*) total,"+
			"count(case when attendance_status='Marked' then 1 end) marked,"+ 
			"count(case when attendance_status='Not Marked' then 1 end) unmarked," +
			"count(case when attendance_status IN ('Leave','Leave - Approval Pending','Floater Holiday - Approval Pending','Floater Holiday') then 1 end) leave_count  " +
			//"count(case when attendance_status='Leave - Approval Pending' then 1 end) leave_app_pend" + " " + 
			"from attendance_status st" + " " + 
			"where" + " " +
			"client_location= :clinetLocation"+" AND " +
			"category= :category"+" AND " +
			"attendance_date= :startDate"+ " " +
			"group by client_location;";
	
	public static final String DELIVERY_SUMMARY_QUERY = "SELECT count(*) Total," +
			"acc.name account,"+ 
			"count(case when status='RED' then 1 end) redCount,"+ 
			"count(case when status='AMBER' then 1 end) amberCount," +
			"count(case when status='GREEN' then 1 end) greenCount" + " " + 
			"FROM daily_status stat, project proj, account acc" + " " +
			"where" + " " + 
			"stat.project_id = proj.id" + " " +
			"and proj.account_id = acc.id" + " " +
			"and date= :fromDate" +" "+
			"group by account, date;";
}

