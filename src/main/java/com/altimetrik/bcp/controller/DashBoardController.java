package com.altimetrik.bcp.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.altimetrik.bcp.model.AttendanceData;
import com.altimetrik.bcp.model.AttendanceType;
import com.altimetrik.bcp.model.DeliverySummary;
import com.altimetrik.bcp.model.PlanDetailFormData;
import com.altimetrik.bcp.service.BCMService;
import com.altimetrik.bcp.service.FileUploadService;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {
	
	@Autowired
	private BCMService bcmService;
	
	@Autowired
	private FileUploadService fileUploadService;

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
		return ResponseEntity.ok().body(attendenceMap);
	}

	@GetMapping(value = "/getAttendencePercent")
	public ResponseEntity<Map<String, List<String>>> getAttendencePercent(
			@RequestParam("attendanceWise") String wiseType, @RequestParam("attdTypeValue") String typeValue,
			@RequestParam("billingType") String billingStatus, @RequestParam("attdType") String type,
			@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy/MM/dd") Date fromDate) throws ParseException {
		Map<String, List<String>> attendenceMap = new TreeMap<>();
		attendenceMap = bcmService.getAttendancePercent(wiseType, typeValue, billingStatus, type, fromDate);
		return ResponseEntity.ok().body(attendenceMap);
	}
	
	@GetMapping(value = "/getAccountNames")
	public ResponseEntity<List<String>> getAccountNames(){
		return ResponseEntity.ok().body(bcmService.getAccountNames());
	}
	
	@GetMapping(value = "/getClientLocations")
	public ResponseEntity<List<String>> getClientLocations(){
		return ResponseEntity.ok().body(bcmService.getClientLocations());
	}
	
	@GetMapping(value="/getDeliveryList")
	public ResponseEntity<List<DeliverySummary>> getDeliverySummaryList(
			@RequestParam("fromDate") @DateTimeFormat(pattern="yyyy/MM/dd") Date fromDate){
		return ResponseEntity.ok().body(bcmService.getDeliverList(fromDate));
	}
	
	@GetMapping(value="getDeliveryByProject")
	public ResponseEntity<List<PlanDetailFormData>> getDeliveryByProjectWise(@RequestParam("accountName") 
	String accountName, @RequestParam("status") String statusValue, @RequestParam("fromDate") @DateTimeFormat(pattern="yyyy/MM/dd") Date fromDate){
		return ResponseEntity.ok().body(bcmService.getSummayByProject(accountName, fromDate, statusValue));
	}
	
	@PostMapping("/uploadFile")
	public ResponseEntity<?> uploadFile(@RequestParam("uploadFile") MultipartFile file,@RequestParam("fileUploadType") String uploadFileType
			,@RequestParam("uploadedBy") String uploadedBy){
		try {
			String uploadedFilePath = fileUploadService.storeFile(file,uploadFileType,uploadedBy);
			if(uploadFileType.equals("Attendance")){
				fileUploadService.readAttendanceFromExcel(uploadedFilePath);
			} else if(uploadFileType.equals("Delivery")) {
				//TODO :: delivery file upload impl
				fileUploadService.readDailyStatusFromExcel(uploadedFilePath);
			}
			
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}
	}
}