package com.altimetrik.bcp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class BcpPropertyConfig {

	@Value("${bcp.attendance.upload.file.path}")
	private String attendanceFilePath;
	
    public String getAttendanceFilePath() {
		return attendanceFilePath;
	}

	public void setAttendanceFilePath(String attendanceFilePath) {
		this.attendanceFilePath = attendanceFilePath;
	}
    
}
