package com.altimetrik.bcp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.bcp.dao.AccountRepo;
import com.altimetrik.bcp.dao.LeaderRepo;
import com.altimetrik.bcp.dao.LocationRepo;
import com.altimetrik.bcp.dao.ProjectAssocRepo;
import com.altimetrik.bcp.dao.ProjectRepo;
import com.altimetrik.bcp.entity.Account;
import com.altimetrik.bcp.entity.Leader;
import com.altimetrik.bcp.entity.Location;
import com.altimetrik.bcp.entity.ProjLocLeaderAssoc;
import com.altimetrik.bcp.entity.Project;
import com.altimetrik.bcp.model.PlanDetailFormData;
import com.altimetrik.bcp.service.BCMService;

/**
 * 
 * @author smehta
 *
 */
@RestController
@RequestMapping("/bcm")
public class BCMController {
	
	@Autowired
	BCMService bcmService;
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	LeaderRepo leaderRepo;
	
	@Autowired
	ProjectAssocRepo asscoRepo;
	
	@Autowired
	LocationRepo locationRepo;
	
	@Autowired
	AccountRepo acRepo;
	
	@Autowired
	ProjectRepo projectRepo;

	@Autowired
	ProjectAssocRepo projecAssocRepo;

	/*
	 * createProjectLocationAssociate
	 */
	@PostMapping(value = "/addDilyStatus")
	public void createDailyStatus(@RequestBody PlanDetailFormData formData) {
		bcmService.createDilyStatus(formData);
		
	}
	
	@GetMapping(value = "/getAllAccounts")
	public ResponseEntity<List<Account>> getAllLocation(){
		List<Account> accountList = accountRepo.findAll();
		return ResponseEntity.ok().body(accountList);
	}
	
	@GetMapping(value = "/getLeader/{accountId}")
	public Leader getLeaderById(@PathVariable("accountId") int accountId){
		List<ProjLocLeaderAssoc> assocList = asscoRepo.findAll();
		
		for(ProjLocLeaderAssoc leaderAssoc:assocList){
			if(leaderAssoc.getAccount().getId() == accountId){
				return leaderAssoc.getLeader();
			}
		}
		return null;
		
	}
	
	@GetMapping(value = "/getProject/{accountId}")
	public ResponseEntity<List<Project>> getProject(@PathVariable("accountId") int accountId){
		List<ProjLocLeaderAssoc> assocList = projecAssocRepo.findAll();
		List<Project> projList = new ArrayList<Project>();
		for(ProjLocLeaderAssoc projAssoc:assocList){
			if(projAssoc.getAccount().getId() == accountId){
				projList.add(projAssoc.getProject());
			}
		}
		return ResponseEntity.ok().body(projList);
	}

}
