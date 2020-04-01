package com.altimetrik.bcp.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.altimetrik.bcp.model.AttendanceByAccount;
import com.altimetrik.bcp.model.AttendanceByLocation;
import com.altimetrik.bcp.util.MysqlQueryConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@SqlResultSetMapping(
	    name = "AttendanceMapping",
	    classes = @ConstructorResult(
	            targetClass = AttendanceByAccount.class,
	            columns = {
	            		@ColumnResult(name = "account_name",type=String.class),
	                    @ColumnResult(name = "total",type=Integer.class),
	                    @ColumnResult(name = "marked",type=Integer.class),
	                    @ColumnResult(name = "unMarked",type=Integer.class),
	                    @ColumnResult(name = "leave_count",type=Integer.class),
	                    @ColumnResult(name = "leave_app_pend",type=Integer.class)
	                    
	            }
	    )
	)
@SqlResultSetMapping(
	    name = "locationMapping",
	    classes = @ConstructorResult(
	            targetClass = AttendanceByLocation.class,
	            columns = {
	            		@ColumnResult(name = "client_location",type=String.class),
	                    @ColumnResult(name = "total",type=Integer.class),
	                    @ColumnResult(name = "marked",type=Integer.class),
	                    @ColumnResult(name = "unMarked",type=Integer.class),
	                    @ColumnResult(name = "leave_count",type=Integer.class),
	                    @ColumnResult(name = "leave_app_pend",type=Integer.class)
	                    
	            }
	    )
	)
@NamedNativeQuery(name = "findAllDataMapping", resultSetMapping ="AttendanceMapping",resultClass = AttendanceByAccount.class, query=MysqlQueryConstants.ATTENDANCE_COUNT_QUERY)
@NamedNativeQuery(name = "dataByLocationMapping", resultSetMapping ="locationMapping",resultClass = AttendanceByLocation.class, query=MysqlQueryConstants.ATTENDANCE_LOCATION_QUERY)
@NamedNativeQuery(name = "getAttendanceByAccountName", resultSetMapping ="AttendanceMapping",resultClass = AttendanceByAccount.class, query=MysqlQueryConstants.GET_ATTENDANCE_COUNT_BY_ACC_NAME)
@NamedNativeQuery(name = "getAttendanceByAccountNameAndAttdStatus", resultSetMapping ="AttendanceMapping",resultClass = AttendanceByAccount.class, query=MysqlQueryConstants.GET_ATTENDANCE_COUNT_ATTD_STATUS_AND_DATE)


@NamedNativeQuery(name = "getAttendanceByLocationName", resultSetMapping ="locationMapping",resultClass = AttendanceByLocation.class, query=MysqlQueryConstants.GET_ATTENDANCE_COUNT_BY_LOCATION)
@NamedNativeQuery(name = "getAttendanceByLocationAndAttdStatus", resultSetMapping ="locationMapping",resultClass = AttendanceByLocation.class, query=MysqlQueryConstants.GET_ATTENDANCE_LOCATION_COUNT_ATTD_STATUS_AND_DATE)

public class AttendanceStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int S_NO;
	
	@JsonProperty("empId")
	@Column(name="Emp_ID")
	int employeeId;
	
	@JsonProperty("empployeeName")
	@Column(name="EMPLOYEE_NAME")
	String empployeeName;
	
	@Column(name="GEOGRAPHY")
	String geography;
	
	@JsonProperty("accountName")
	@Column(name="ACCOUNT_NAME")
	String accountName;
	
	@Column(name="Country")
	String country;
	
	@JsonProperty("clientLocation")
	@Column(name="CLIENT_LOCATION")
	String clinetLocation;
	
	@Column(name="Total_Hc")
	int totalHc;
	
	@JsonProperty("project")
	@Column(name="PROJECT")
	String project;
	
	@Column(name="BASE_CATEGORY")
	String baseCategory;
	
	@Column(name="CAPABILITY_CENTRE")
	String capabilityCenter;
	
	@Column(name="On_Bench_web_Date",nullable=true)
	Timestamp benchWebDate;
	
	@Column(name="Assignment_Status")
	String assignmentStatus;
	
	@Column(name="category")
	String category;
	
	@Column(name="DOJ")
	Timestamp dateOfJoining;
	
	@Column(name="Bench_WFB_Aging",nullable=true)
	Integer benchWebAging;
	
	@Column(name="PRIMARY_SKILL")
	String primarySkill;
	
	@Column(name="SECONDARY_SKILL")
	String secondarySkill;
	
	@Column(name="TOTAL_EXP_in_YRS")
	int totalExperience;
	
	@JsonProperty("reportManager")
	@Column(name="REPORTING_MANAGER")
	String reportingManager;
	
	@Column(name="BASE_LOCATION")
	String baseLocation;
	
	@JsonProperty("attendanceStatus")
	@Column(name="Attendance_Status")
	String attendanceStatus;
	
	@JsonProperty("attendanceDate")
	@Temporal(TemporalType.DATE)
	@JsonFormat(
			  shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy", timezone = "IST", locale = "en_GB")
	@Column(name="Attendance_Date")
	Date attendanceDate;

	@Override
	public String toString() {
		return "AttendanceStatus [S_NO=" + S_NO + ", employeeId=" + employeeId + ", empployeeName=" + empployeeName
				+ ", accountName=" + accountName + ", dateOfJoining=" + dateOfJoining + ", reportingManager="
				+ reportingManager + ", attendanceStatus=" + attendanceStatus + ", attendanceDate=" + attendanceDate
				+ "]";
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpployeeName() {
		return empployeeName;
	}

	public void setEmpployeeName(String empployeeName) {
		this.empployeeName = empployeeName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getClinetLocation() {
		return clinetLocation;
	}

	public void setClinetLocation(String clinetLocation) {
		this.clinetLocation = clinetLocation;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public Date getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(Date attendanceDate) {
		this.attendanceDate = attendanceDate;
	}
}
