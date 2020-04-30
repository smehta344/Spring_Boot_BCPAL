package com.altimetrik.bcp.entity;

import java.io.Serializable;
import java.util.Date;

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

import org.hibernate.annotations.GenericGenerator;

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
	                    @ColumnResult(name = "total",type=Double.class),
	                    @ColumnResult(name = "marked",type=Double.class),
	                    @ColumnResult(name = "unMarked",type=Double.class),
	                    @ColumnResult(name = "leave_count",type=Double.class)//,
	                    //@ColumnResult(name = "leave_app_pend",type=Integer.class)
	                    
	            }
	    )
	)
@SqlResultSetMapping(
	    name = "locationMapping",
	    classes = @ConstructorResult(
	            targetClass = AttendanceByLocation.class,
	            columns = {
	            		@ColumnResult(name = "client_location",type=String.class),
	                    @ColumnResult(name = "total",type=Double.class),
	                    @ColumnResult(name = "marked",type=Double.class),
	                    @ColumnResult(name = "unMarked",type=Double.class),
	                    @ColumnResult(name = "leave_count",type=Double.class)//,
	                    //@ColumnResult(name = "leave_app_pend",type=Integer.class)
	                    
	            }
	    )
	)
@NamedNativeQuery(name = "findAllDataMapping", resultSetMapping ="AttendanceMapping",resultClass = AttendanceByAccount.class, query=MysqlQueryConstants.ATTENDANCE_COUNT_QUERY)
@NamedNativeQuery(name = "dataByLocationMapping", resultSetMapping ="locationMapping",resultClass = AttendanceByLocation.class, query=MysqlQueryConstants.ATTENDANCE_LOCATION_QUERY)
@NamedNativeQuery(name = "getAttendanceByAccountName", resultSetMapping ="AttendanceMapping",resultClass = AttendanceByAccount.class, query=MysqlQueryConstants.GET_ATTENDANCE_COUNT_BY_ACC_NAME)
@NamedNativeQuery(name = "getAttendanceByAccountNameAndAttdStatus", resultSetMapping ="AttendanceMapping",resultClass = AttendanceByAccount.class, query=MysqlQueryConstants.GET_ATTENDANCE_COUNT_ATTD_STATUS_AND_DATE)
@NamedNativeQuery(name = "getAttendanceByLocationName", resultSetMapping ="locationMapping",resultClass = AttendanceByLocation.class, query=MysqlQueryConstants.GET_ATTENDANCE_COUNT_BY_LOCATION)
@NamedNativeQuery(name = "getAttendanceByLocationAndAttdStatus", resultSetMapping ="locationMapping",resultClass = AttendanceByLocation.class, query=MysqlQueryConstants.GET_ATTENDANCE_LOCATION_COUNT_ATTD_STATUS_AND_DATE)

@NamedNativeQuery(name = "findAllAccountDataWithDateAndBillingStatus", resultSetMapping ="AttendanceMapping",resultClass = AttendanceByAccount.class, query=MysqlQueryConstants.GET_ATTENDANCE_COUNT_CATEGORY_AND_DATE)
@NamedNativeQuery(name = "getAttendByParticularAccountWithCategory", resultSetMapping ="AttendanceMapping",resultClass = AttendanceByAccount.class, query=MysqlQueryConstants.GET_ATTENDANCE_COUNT_BY_ACC_NAME_AND_CATEGORY_DATE)

@NamedNativeQuery(name = "getAttendByAllLocationsDateAndBillingStatus", resultSetMapping ="locationMapping",resultClass = AttendanceByLocation.class, query=MysqlQueryConstants.GET_ATTENDANCE_COUNT_BY_LOCATION_AND_CATEGORY_DATE)
@NamedNativeQuery(name = "getAttendByLocationAndDateAndBillingStatus", resultSetMapping ="locationMapping",resultClass = AttendanceByLocation.class, query=MysqlQueryConstants.GET_ATTENDANCE_COUNT_BY_PARTICULAR_LOCATION_AND_CATEGORY_DATE)


public class AttendanceStatus implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator="native"
			)
	@GenericGenerator(
	    name = "native",
	    strategy = "native"
	)
	int S_NO;
	
	@JsonProperty("empId")
	@Column(name="Emp_ID")
	String employeeId;
	
	@JsonProperty("empployeeName")
	@Column(name="EMPLOYEE_NAME")
	String empployeeName;
	
	@JsonProperty("emailId")
	@Column(name="Email_ID")
	String emailId;
	
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
	double totalHc;
	
	@JsonProperty("project")
	@Column(name="PROJECT")
	String project;
	
	@Column(name="BASE_CATEGORY")
	String baseCategory;
	
	@Column(name="CAPABILITY_CENTRE")
	String capabilityCenter;
	
	@Column(name="On_Bench_web_Date",nullable=true)
	Date benchWebDate;
	
	@Column(name="Assignment_Status")
	String assignmentStatus;
	
	@JsonProperty("Category")
	@Column(name="Category")
	String category;
	
	@Column(name="DOJ")
	Date dateOfJoining;
	
	@Column(name="Bench_WFB_Aging",nullable=true)
	Integer benchWebAging;
	
	@Column(name="PRIMARY_SKILL")
	String primarySkill;
	
	@Column(name="SECONDARY_SKILL")
	String secondarySkill;
	
	@Column(name="TOTAL_EXP_in_YRS")
	String totalExperience;
	
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

	public int getS_NO() {
		return S_NO;
	}

	public void setS_NO(int s_NO) {
		S_NO = s_NO;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmpployeeName() {
		return empployeeName;
	}

	public void setEmpployeeName(String empployeeName) {
		this.empployeeName = empployeeName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGeography() {
		return geography;
	}

	public void setGeography(String geography) {
		this.geography = geography;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getClinetLocation() {
		return clinetLocation;
	}

	public void setClinetLocation(String clinetLocation) {
		this.clinetLocation = clinetLocation;
	}

	public double getTotalHc() {
		return totalHc;
	}

	public void setTotalHc(double totalHc) {
		this.totalHc = totalHc;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getBaseCategory() {
		return baseCategory;
	}

	public void setBaseCategory(String baseCategory) {
		this.baseCategory = baseCategory;
	}

	public String getCapabilityCenter() {
		return capabilityCenter;
	}

	public void setCapabilityCenter(String capabilityCenter) {
		this.capabilityCenter = capabilityCenter;
	}

	public Date getBenchWebDate() {
		return benchWebDate;
	}

	public void setBenchWebDate(Date benchWebDate) {
		this.benchWebDate = benchWebDate;
	}

	public String getAssignmentStatus() {
		return assignmentStatus;
	}

	public void setAssignmentStatus(String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public Integer getBenchWebAging() {
		return benchWebAging;
	}

	public void setBenchWebAging(Integer benchWebAging) {
		this.benchWebAging = benchWebAging;
	}

	public String getPrimarySkill() {
		return primarySkill;
	}

	public void setPrimarySkill(String primarySkill) {
		this.primarySkill = primarySkill;
	}

	public String getSecondarySkill() {
		return secondarySkill;
	}

	public void setSecondarySkill(String secondarySkill) {
		this.secondarySkill = secondarySkill;
	}

	public String getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(String totalExperience) {
		this.totalExperience = totalExperience;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}

	public String getBaseLocation() {
		return baseLocation;
	}

	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
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

	@Override
	public String toString() {
		return "AttendanceStatus [S_NO=" + S_NO + ", employeeId=" + employeeId + ", empployeeName=" + empployeeName
				+ ", emailId=" + emailId + ", geography=" + geography + ", accountName=" + accountName + ", country="
				+ country + ", clinetLocation=" + clinetLocation + ", totalHc=" + totalHc + ", project=" + project
				+ ", baseCategory=" + baseCategory + ", capabilityCenter=" + capabilityCenter + ", benchWebDate="
				+ benchWebDate + ", assignmentStatus=" + assignmentStatus + ", category=" + category
				+ ", dateOfJoining=" + dateOfJoining + ", benchWebAging=" + benchWebAging + ", primarySkill="
				+ primarySkill + ", secondarySkill=" + secondarySkill + ", totalExperience=" + totalExperience
				+ ", reportingManager=" + reportingManager + ", baseLocation=" + baseLocation + ", attendanceStatus="
				+ attendanceStatus + ", attendanceDate=" + attendanceDate + "]";
	}
}
