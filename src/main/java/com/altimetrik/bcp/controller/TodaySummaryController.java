package com.altimetrik.bcp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.bcp.entity.TodaySummary;
import com.altimetrik.bcp.model.TodaySummaryDto;
import com.altimetrik.bcp.service.BCMService;

@RestController
@CrossOrigin
@RequestMapping("/summary")
public class TodaySummaryController {
	
	@Autowired
	private BCMService bcmService;

	@PostMapping(value = "/addTodaySummary")
	public ResponseEntity<String> createDailyStatus(@RequestBody TodaySummaryDto formData) {
		bcmService.addTodaySummary(formData);
		return ResponseEntity.ok().body("success");
	}
	
	@GetMapping(value = "/getTodaySummary")
	public ResponseEntity<TodaySummary> getTodaySummary(
			@RequestParam("fromDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date date) {
		TodaySummary todaySummary = bcmService.getTodaySummary(date);
		return ResponseEntity.ok().body(todaySummary);
	}
	
}
