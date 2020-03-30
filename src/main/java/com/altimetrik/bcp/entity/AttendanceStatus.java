package com.altimetrik.bcp.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import com.altimetrik.bcp.model.AttendanceByAccount;
import com.altimetrik.bcp.model.AttendanceByLocation;
import com.altimetrik.bcp.util.MysqlQueryConstants;

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
public class AttendanceStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int S_NO;
	
	@Column(name="Emp_ID")
	int employeeId;
	
	@Column(name="EMPLOYEE_NAME")
	String empployeeName;
	
	@Column(name="GEOGRAPHY")
	String geography;
	
	@Column(name="ACCOUNT_NAME")
	String accountName;
	
	@Column(name="Country")
	String country;
	
	@Column(name="CLIENT_LOCATION")
	String clinetLocation;
	
	@Column(name="Total_Hc")
	int totalHc;
	
	@Column(name="PROJECT")
	String project;
	
	@Column(name="BASE_CATEGORY")
	String baseCategory;
	
	@Column(name="CAPABILITY_CENTRE")
	String capabilityCenter;
	
	@Column(name="On_Bench_web_Date")
	Timestamp benchWebDate;
	
	@Column(name="Assignment_Status")
	String assignmentStatus;
	
	@Column(name="category")
	String category;
	
	@Column(name="DOJ")
	Timestamp dateOfJoining;
	
	@Column(name="Bench_WFB_Aging")
	int benchWebAging;
	
	@Column(name="PRIMARY_SKILL")
	String primarySkill;
	
	@Column(name="SECONDARY_SKILL")
	String secondarySkill;
	
	@Column(name="TOTAL_EXP_in_YRS")
	int totalExperience;
	
	@Column(name="REPORTING_MANAGER")
	String reportingManager;
	
	@Column(name="BASE_LOCATION")
	String baseLocation;
	
	@Column(name="Attendance_Status")
	String attendanceStatus;
	
	@Column(name="Attendance_Date")
	Timestamp attendanceDate;
}
