package com.altimetrik.bcp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.bcp.dao.AccountRepo;
import com.altimetrik.bcp.dao.LocationRepo;
import com.altimetrik.bcp.entity.Account;
import com.altimetrik.bcp.entity.Leader;
import com.altimetrik.bcp.entity.Location;
import com.altimetrik.bcp.entity.Project;
import com.altimetrik.bcp.model.PlanDetailFormData;
import com.altimetrik.bcp.service.BCMService;

/**
 * 
 * @author smehta
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/bcm")
public class BCMController {

	private static final Logger logger = LoggerFactory.getLogger(BCMController.class);

	@Autowired
	private BCMService bcmService;

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	private LocationRepo locationRepo;

	@PostMapping(value = "/addDilyStatus")
	public ResponseEntity<String> createDailyStatus(@RequestBody PlanDetailFormData formData) {
		bcmService.createDilyStatus(formData);
		logger.info("Data added sucessfully");
		return ResponseEntity.ok().body("Success");
	}

	@GetMapping(value = "/getAllLocations")
	public ResponseEntity<List<Location>> getAllLocation() {
		List<Location> locationList = locationRepo.findAll();
		logger.info("Get all locations sucessfully");
		return ResponseEntity.ok().body(locationList);
	}

	@GetMapping(value = "/getAllAccounts")
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> accountList = accountRepo.findAll();
		logger.info("Get all accounts sucessfully");
		return ResponseEntity.ok().body(accountList);
	}

	@GetMapping(value = "/getLeader/{locationId}/{accountId}")
	public ResponseEntity<Leader> getLeaderById(@PathVariable("locationId") int locationId,
			@PathVariable("accountId") int accountId) {
		Leader leaderData = bcmService.getLeader(locationId, accountId);
		logger.info("Retrive leader data sucessfully");
		return ResponseEntity.ok().body(leaderData);

	}

	@GetMapping(value = "/getProject/{accountId}")
	public ResponseEntity<List<Project>> getProject(@PathVariable("accountId") int accountId) {
		List<Project> projectList = bcmService.getProjectById(accountId);
		logger.info("Get project data sucessfully");
		return ResponseEntity.ok().body(projectList);
	}
}
