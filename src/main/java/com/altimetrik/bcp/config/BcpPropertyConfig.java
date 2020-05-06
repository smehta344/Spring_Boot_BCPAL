package com.altimetrik.bcp.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.altimetrik.bcp.util.BcpUtils;

@Configuration
@PropertySource("classpath:application.properties")
public class BcpPropertyConfig {

	@Value("${bcp.attendance.upload.file.path}")
	private String attendanceFilePath;
	
	@Value("${bcp.attendance.skip.employee.ids}")
	private List<String> attendanceSkipEmployeeIdsMatchList;
	
	@Value("${bcp.attendance.skip.account.names}")
	private List<String> attendanceSkipAccountNamesList;
	
	@Value("${bcp.attendance.include.country.names}")
	private List<String> attendanceAllowCountryNamesList;
	
    public String getAttendanceFilePath() {
		return attendanceFilePath;
	}

	public void setAttendanceFilePath(String attendanceFilePath) {
		this.attendanceFilePath = attendanceFilePath;
	}

	public List<String> getAttendanceSkipEmployeeIdsMatchList() {
		return BcpUtils.convertListOfStringToLowercase(attendanceSkipEmployeeIdsMatchList);
	}

	public void setAttendanceSkipEmployeeIdsMatchList(List<String> attendanceSkipEmployeeIdsMatchList) {
		this.attendanceSkipEmployeeIdsMatchList = attendanceSkipEmployeeIdsMatchList;
	}

	public List<String> getAttendanceSkipAccountNamesList() {
		return BcpUtils.convertListOfStringToLowercase(attendanceSkipAccountNamesList);
	}

	public void setAttendanceSkipAccountNamesList(List<String> attendanceSkipAccountNamesList) {
		this.attendanceSkipAccountNamesList = attendanceSkipAccountNamesList;
	}

	public List<String> getAttendanceAllowCountryNamesList() {
		return BcpUtils.convertListOfStringToLowercase(attendanceAllowCountryNamesList);
	}

	public void setAttendanceAllowCountryNamesList(List<String> attendanceAllowCountryNamesList) {
		this.attendanceAllowCountryNamesList = attendanceAllowCountryNamesList;
	}
    
}
