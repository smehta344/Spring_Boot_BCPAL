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

	private static final Logger logger = LoggerFactory.getLogger(DashBoardController.class);

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
		logger.info("Get Attendence" + attendenceMap.toString());
		// CustomLogging.asyncLogger("Get Attendance", attendenceMap,
		// DashBoardController.class);
		return ResponseEntity.ok().body(attendenceMap);
	}

	@GetMapping(value = "/getAttendencePercent")
	public ResponseEntity<Map<String, List<String>>> getAttendencePercent(
			@RequestParam("attendanceWise") String wiseType, @RequestParam("attdTypeValue") String typeValue,
			@RequestParam("billingType") String billingStatus, @RequestParam("attdType") String type,
			@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy/MM/dd") Date fromDate) throws ParseException {
		Map<String, List<String>> attendenceMap = new TreeMap<>();
		attendenceMap = bcmService.getAttendancePercent(wiseType, typeValue, billingStatus, type, fromDate);
		logger.info("Get AttendencePercent" + attendenceMap.toString());
		// CustomLogging.asyncLogger("Get AttendencePercent", attendenceMap,
		// DashBoardController.class);
		return ResponseEntity.ok().body(attendenceMap);
	}

	@GetMapping(value = "/getAccountNames")
	public ResponseEntity<List<String>> getAccountNames() {
		logger.info("Get AccountNames" + bcmService.toString());
		// CustomLogging.asyncLogger("Get AccountNames", bcmService,
		// DashBoardController.class);
		return ResponseEntity.ok().body(bcmService.getAccountNames());
	}

	@GetMapping(value = "/getClientLocations")
	public ResponseEntity<List<String>> getClientLocations() {
		logger.info("Get ClientLocations" + bcmService.toString());
		// CustomLogging.asyncLogger("Get ClientLocations", bcmService,
		// DashBoardController.class);
		return ResponseEntity.ok().body(bcmService.getClientLocations());
	}

	@GetMapping(value = "/getDeliveryList")
	public ResponseEntity<List<DeliverySummary>> getDeliverySummaryList(
			@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy/MM/dd") Date fromDate) {
		logger.info("Get DeliveryList" + fromDate.toString());
		// CustomLogging.asyncLogger("Get DeliveryList", fromDate,
		// DashBoardController.class);
		return ResponseEntity.ok().body(bcmService.getDeliverList(fromDate));
	}

	@GetMapping(value = "getDeliveryByProject")
	public ResponseEntity<List<PlanDetailFormData>> getDeliveryByProjectWise(
			@RequestParam("accountName") String accountName, @RequestParam("status") String statusValue,
			@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy/MM/dd") Date fromDate) {
		logger.info("Get DeliveryByProject" + bcmService.toString());
		// CustomLogging.asyncLogger("Get DeliveryByProject", bcmService,
		// DashBoardController.class);
		return ResponseEntity.ok().body(bcmService.getSummayByProject(accountName, fromDate, statusValue));
	}

	@PostMapping("/uploadFile")
	public ResponseEntity<?> uploadFile(@RequestParam("uploadFile") MultipartFile file,
			@RequestParam("fileUploadType") String uploadFileType) {
		try {
			String uploadedFilePath = fileUploadService.storeFile(file, uploadFileType);
			if (uploadFileType.equals("Attendance")) {
				fileUploadService.readAttendanceFromExcel(uploadedFilePath);
			} else if (uploadFileType.equals("Delivery")) {
				// TODO :: delivery file upload impl
			}
			logger.info("File uploaded successfully " + uploadFileType.toString());
			// CustomLogging.asyncLogger("Get DeliveryByProject", bcmService,
			// DashBoardController.class);
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			logger.info("File not found " + uploadFileType.toString());
			// CustomLogging.asyncLogger("Get DeliveryByProject", bcmService,
			// DashBoardController.class);
			return ResponseEntity.badRequest().body(e.getLocalizedMessage());
		}
	}
}