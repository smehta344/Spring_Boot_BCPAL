package com.altimetrik.bcp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.altimetrik.bcp.entity.AttendanceStatus;
import com.altimetrik.bcp.model.AttendanceByAccount;
import com.altimetrik.bcp.model.AttendanceByLocation;

@Repository
public interface AttendanceRepo extends JpaRepository<AttendanceStatus, Integer> {

	@Query(nativeQuery = true, name = "findAllDataMapping")
	public List<AttendanceByAccount> getAttendByAccount(@Param("startDate") String startDate);

	@Query(nativeQuery = true, name = "dataByLocationMapping")	
	public List<AttendanceByLocation> getAttendByLocation(@Param("startDate") String startDate);

}