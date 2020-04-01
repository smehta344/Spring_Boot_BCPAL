package com.altimetrik.bcp.dao;

import java.util.Date;
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
	
	@Query(nativeQuery = true,value="SELECT DISTINCT a.account_name FROM attendance_status a WHERE a.account_name IS NOT NULL ORDER BY a.account_name ASC")
	List<String> findDistinctAccountName();
	
	@Query(nativeQuery = true,value="SELECT DISTINCT a.client_location FROM attendance_status a ORDER BY a.client_location ASC")
	List<String> findDistinctClientLocation();

	@Query(nativeQuery = true, name = "findAllDataMapping")
	public List<AttendanceByAccount> getAttendByAllAccounts(@Param("startDate") String startDate);

	@Query(nativeQuery = true, name = "dataByLocationMapping")	
	public List<AttendanceByLocation> getAttendByLocation(@Param("startDate") String startDate);
	
	@Query(nativeQuery = true, name = "getAttendanceByAccountName")
	public AttendanceByAccount getAttendByParticularAccount(@Param("accountName") String accountName,@Param("startDate") String startDate);
	
	@Query(nativeQuery = true, name = "getAttendanceByAccountNameAndAttdStatus")
	public List<AttendanceByAccount> getAttendByAllAccountsAndAttdStatus(@Param("startDate") String startDate);

	@Query(nativeQuery = true, name = "getAttendanceByLocationName")
	public AttendanceByLocation getAttendByParticularLocation(@Param("clinetLocation") String clinetLocation,@Param("startDate") String startDate);
	
	@Query(nativeQuery = true, name = "getAttendanceByLocationAndAttdStatus")
	public List<AttendanceByLocation> getAttendByAllLocationsAndAttdStatus(/*@Param("attendanceStatus") String attendanceStatus,*/@Param("startDate") String startDate);
	
	public List<AttendanceStatus> getAttendanceStatusByAccountNameAndAttendanceDate(@Param("accountName") String accountName,@Param("startDate") Date startDate);
	
	public List<AttendanceStatus> getAttendanceStatusByAttendanceStatusAndAttendanceDate(@Param("attendanceStatus") String attendanceStatus,@Param("startDate") Date startDate);
	
	public List<AttendanceStatus> getAttendanceStatusByAccountNameAndAttendanceStatusAndAttendanceDate(@Param("accountName") String accountName,@Param("attendanceStatus") String attendanceStatus,@Param("startDate") Date startDate);

	public List<AttendanceStatus> getAttendanceStatusByClinetLocationAndAttendanceDate(@Param("clinetLocation") String clinetLocation,@Param("startDate") Date startDate);
	
	public List<AttendanceStatus> getAttendanceStatusByClinetLocationAndAttendanceStatusAndAttendanceDate(@Param("clinetLocation") String clinetLocation,@Param("attendanceStatus") String attendanceStatus,@Param("startDate") Date startDate);


}