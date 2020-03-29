package com.altimetrik.bcp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.entity.Account;
import com.altimetrik.bcp.entity.Attendance;
import com.altimetrik.bcp.entity.AttendanceStatus;

@Repository
public interface AttendanceRepo extends JpaRepository<AttendanceStatus, Integer> {

	String sqlQuery = "count(case when attendance_status='Marked' then 1 end) marked,"+ 
			"count(case when attendance_status='Not Marked' then 1 end) unmarked," +
			"count(case when attendance_status='Leave' then 1 end) leave_count," +
			"count(case when attendance_status='Leave - Approval Pending' then 1 end) leave_app_pend" + " " + 
			"from attendance_status st" + " " + 
			"where" + " " +
			"attendance_date= :startDate"+ " " +
			"group by account_name;";
	
	@Query(value = "select account_name,count(*) total," + sqlQuery,
		nativeQuery = true)	
	public List<Object> getAttendByAccount(@Param("startDate") String startDate);

	@Query(value = "select client_location,count(*) total," + sqlQuery,
			nativeQuery = true)	
	public List<Object> getAttendByLocation(@Param("startDate") String startDate);

}