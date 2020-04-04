package com.altimetrik.bcp.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.bcp.model.AttendanceData;
import com.altimetrik.bcp.model.AttendanceType;
import com.altimetrik.bcp.service.BCMService;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {

	private static final Logger logger = LoggerFactory.getLogger(DashBoardController.class);

	@Autowired
	private BCMService bcmService;

	@GetMapping(value = "/getAttendence")
	public ResponseEntity<Map<String, AttendanceData>> getAttendenceStatus(
			@RequestParam("attendanceWise") String wiseType, @RequestParam("attdTypeValue") String typeValue,
			@RequestParam("billingType") String billingStatus, @RequestParam("attdType") String type,
			@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy/MM/dd") Date fromDate) {
		Map<String, AttendanceData> attendenceMap = new TreeMap<>();
		if (AttendanceType.ACCOUNT == AttendanceType.valueOf(wiseType)) {
			attendenceMap = bcmService.getAttendanceByAccount(typeValue, billingStatus, type, fromDate);
		} else {
			attendenceMap = bcmService.getAttendanceByLocation(typeValue, billingStatus, type, fromDate);
		}
		logger.info("Get all attendence sucessfully");
		return ResponseEntity.ok().body(attendenceMap);
	}

	@GetMapping(value = "/getAttendencePercent")
	public ResponseEntity<Map<String, List<String>>> getAttendencePercent(
			@RequestParam("attendanceWise") String wiseType, @RequestParam("attdTypeValue") String typeValue,
			@RequestParam("billingType") String billingStatus, @RequestParam("attdType") String type,
			@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy/MM/dd") Date fromDate) throws ParseException {
		Map<String, List<String>> attendenceMap = new TreeMap<>();
		attendenceMap = bcmService.getAttendancePercent(wiseType, typeValue, billingStatus, type, fromDate);
		logger.info("Get all attendence sucessfully");
		return ResponseEntity.ok().body(attendenceMap);
	}

	@GetMapping(value = "/getAccountNames")
	public ResponseEntity<List<String>> getAccountNames() {
		logger.info("Get Account Names sucessfully");
		return ResponseEntity.ok().body(bcmService.getAccountNames());
	}

	@GetMapping(value = "/getClientLocations")
	public ResponseEntity<List<String>> getClientLocations() {
		logger.info("Get Client Locations sucessfully");
		return ResponseEntity.ok().body(bcmService.getClientLocations());
	}

}