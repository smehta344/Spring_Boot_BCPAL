package com.altimetrik.bcp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.bcp.dao.AccountRepo;
import com.altimetrik.bcp.dao.LocationRepo;
import com.altimetrik.bcp.entity.Account;
//import com.altimetrik.bcp.dao.BcpRepo;
import com.altimetrik.bcp.model.PlanDetailEntity;

@RestController
@RequestMapping("/plan")
public class PlanDetailController {
	@Autowired 
	private AccountRepo acRepo;
	
	@Autowired
	private LocationRepo locationRepo;

	@GetMapping(value = "/getLocationAndAccount")
	public PlanDetailEntity getLocationaAndAccount() {
		 PlanDetailEntity planDetail = new PlanDetailEntity();
		 planDetail.setAccountData(acRepo.findAll());
		 planDetail.setLocationData(locationRepo.findAll());
         return planDetail;
	}

}
