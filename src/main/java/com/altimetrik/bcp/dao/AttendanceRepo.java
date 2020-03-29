package com.altimetrik.bcp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.entity.Attendance;
import com.altimetrik.bcp.entity.AttendanceStatus;

@Repository
public interface AttendanceRepo extends JpaRepository<AttendanceStatus, Integer> {

	@Query(nativeQuery = true, name = "findAllDataMapping")
	public List<Attendance> getAttendByAccount(@Param("startDate") String startDate);

	/*@Query(value = "select client_location,count(*) total," + sqlQuery,
			nativeQuery = true)	
	public List<Object> getAttendByLocation(@Param("startDate") String startDate);*/

}