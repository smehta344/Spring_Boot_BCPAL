package com.altimetrik.bcp.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.bcp.model.AttendanceData;
import com.altimetrik.bcp.model.LoginService;
import com.altimetrik.bcp.service.BCMService;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {
	
	@Autowired
	BCMService bcmService;
	
	@GetMapping(value = "/getAttendence")
	public ResponseEntity<Map<String, AttendanceData>> getAttendenceStatus(@RequestParam("type") String type, 
			@RequestParam("fromDate") @DateTimeFormat(pattern="yyyy/MM/dd") Date fromDate){
		Map<String, AttendanceData> attendenceMap = bcmService.getAttendeceData(type, fromDate);
		return ResponseEntity.ok().body(attendenceMap);
	}

}
