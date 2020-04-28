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
	
	@Query(nativeQuery = true,value="SELECT DISTINCT a.client_location FROM attendance_status a where a.category = :category ORDER BY a.client_location ASC")
	public List<String> findDistinctClientLocationByCategory(@Param("category") String category);

	@Query(nativeQuery = true,value="SELECT DISTINCT a.account_name FROM attendance_status a where a.account_name IS NOT NULL AND a.category = :category ORDER BY a.account_name ASC")
	public List<String> findDistinctAccountNameByCategory(@Param("category") String category);

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
	
	@Query(nativeQuery = true, name = "findAllAccountDataWithDateAndBillingStatus")
	public List<AttendanceByAccount> getAttendByAllAccountsDateAndBillingStatus(@Param("category") String category,@Param("startDate") String startDate);
	
	@Query(nativeQuery = true, name = "getAttendByParticularAccountWithCategory")
	public AttendanceByAccount getAttendByParticularAccountWithCategory(@Param("accountName") String accountName,@Param("category") String category,@Param("startDate") String startDate);
	
	@Query(nativeQuery = true, name = "getAttendByAllLocationsDateAndBillingStatus")
	public List<AttendanceByLocation> getAttendByAllLocationsDateAndBillingStatus(@Param("category") String category,@Param("startDate") String startDate);
	
	@Query(nativeQuery = true, name = "getAttendByLocationAndDateAndBillingStatus")
	public AttendanceByLocation getAttendByLocationAndDateAndBillingStatus(@Param("clinetLocation") String clinetLocation,@Param("category") String category,@Param("startDate") String startDate);
	
	public List<AttendanceStatus> getAttendanceStatusByAccountNameAndAttendanceDate(@Param("accountName") String accountName,@Param("startDate") Date startDate);
	
	public List<AttendanceStatus> getAttendanceStatusByAttendanceStatusAndAttendanceDate(@Param("attendanceStatus") String attendanceStatus,@Param("startDate") Date startDate);
	
	public List<AttendanceStatus> getAttendanceStatusByAccountNameAndAttendanceStatusAndAttendanceDate(@Param("accountName") String accountName,@Param("attendanceStatus") String attendanceStatus,@Param("startDate") Date startDate);

	public List<AttendanceStatus> getAttendanceStatusByClinetLocationAndAttendanceDate(@Param("clinetLocation") String clinetLocation,@Param("startDate") Date startDate);
	
	public List<AttendanceStatus> getAttendanceStatusByClinetLocationAndAttendanceStatusAndAttendanceDate(@Param("clinetLocation") String clinetLocation,@Param("attendanceStatus") String attendanceStatus,@Param("startDate") Date startDate);

	public List<AttendanceStatus> getAttendanceStatusByClinetLocationAndCategoryAndAttendanceDate(@Param("clinetLocation") String clinetLocation,@Param("category") String category,@Param("startDate") Date startDate);
	
	public List<AttendanceStatus> getAttendanceStatusByClinetLocationAndAttendanceStatusAndCategoryAndAttendanceDate(@Param("clinetLocation") String clinetLocation,@Param("attendanceStatus") String attendanceStatus,@Param("category") String category,@Param("startDate") Date startDate);
	
	public List<AttendanceStatus> getAttendanceStatusByAttendanceStatusAndCategoryAndAttendanceDate(@Param("attendanceStatus") String attendanceStatus,@Param("category") String category,@Param("startDate") Date startDate);
	
	public List<AttendanceStatus> getAttendanceStatusByAccountNameAndAttendanceStatusAndCategoryAndAttendanceDate(@Param("accountName") String accountName,@Param("attendanceStatus") String attendanceStatus,@Param("category") String category,@Param("startDate") Date startDate);
	
	public List<AttendanceStatus> getAttendanceStatusByAccountNameAndCategoryAndAttendanceDate(@Param("accountName") String accountName,@Param("category") String category,@Param("startDate") Date startDate);
	
	//@Query(nativeQuery = true,value="SELECT * FROM attendance_status a WHERE a.account_name IS NOT NULL AND a.attendance_status IN (:names) AND a.attendance_date = :date")
    List<AttendanceStatus> getAttendanceStatusByAttendanceStatusInAndAttendanceDate(@Param("names")List<String> names,@Param("date") Date startDate);
	
	//@Query(nativeQuery = true,value="SELECT * FROM attendance_status a WHERE a.account_name IS NOT NULL AND a.account_name = :accountName AND a.attendance_status IN (:names) AND a.attendance_date = :date")
    List<AttendanceStatus> getAttendanceStatusByAccountNameAndAttendanceStatusInAndAttendanceDate(@Param("accountName") String accountName,@Param("names")List<String> names,@Param("date") Date startDate);
	
	//@Query(nativeQuery = true,value="SELECT * FROM attendance_status a WHERE a.account_name IS NOT NULL AND a.attendance_status IN (:names) AND a.category = :category AND a.attendance_date = :date ")
    List<AttendanceStatus> getAttendanceStatusByAttendanceStatusInAndCategoryAndAttendanceDate(@Param("names")List<String> names,@Param("category") String category,@Param("date") Date startDate);
	
	//@Query(nativeQuery = true,value="SELECT * FROM attendance_status a WHERE a.account_name IS NOT NULL AND a.account_name = :accountName AND a.attendance_status IN (:names) AND a.category = :category AND a.attendance_date = :date ")
    List<AttendanceStatus> getAttendanceStatusByAccountNameAndAttendanceStatusInAndCategoryAndAttendanceDate(@Param("accountName") String accountName,@Param("names")List<String> names,@Param("category") String category,@Param("date") Date startDate);
	
	//@Query(nativeQuery = true,value="SELECT * FROM attendance_status a WHERE a.client_location = :clinetLocation AND a.attendance_status IN (:names) AND a.attendance_date = :date")
    List<AttendanceStatus> getAttendanceStatusByClinetLocationAndAttendanceStatusInAndAttendanceDate(@Param("clinetLocation") String clinetLocation,@Param("names")List<String> names,@Param("date") Date startDate);
	
	//@Query(nativeQuery = true,value="SELECT * FROM attendance_status a WHERE a.account_name IS NOT NULL AND a.client_location = :clinetLocation AND a.attendance_status IN (:names) AND a.category = :category AND a.attendance_date = :date ")
    List<AttendanceStatus> getAttendanceStatusByClinetLocationAndAttendanceStatusInAndCategoryAndAttendanceDate(@Param("clinetLocation") String clinetLocation,@Param("names")List<String> names,@Param("category") String category,@Param("date") Date startDate);

    int deleteByEmailIdAndAccountNameAndAttendanceDate(@Param("emailId") String emailId,@Param("accountName") String accountName,@Param("date") Date startDate);
    AttendanceStatus getAttendanceStatusByEmailIdAndAccountNameAndAttendanceDate(@Param("emailId") String emailId,@Param("accountName") String accountName,@Param("date") Date startDate);
}