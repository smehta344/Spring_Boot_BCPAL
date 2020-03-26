package com.altimetrik.bcp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.bcp.dao.BcpRepo;
import com.altimetrik.bcp.model.BusinessContinuityPlan;

@RestController
//@CrossOrigin
public class BCMController {
	@Autowired 
	private BcpRepo bcpRepo;

	@PostMapping(value = "/addplan")
	public BusinessContinuityPlan createUser(@RequestBody BusinessContinuityPlan businessContinuityPlan) {
        BusinessContinuityPlan businessContinuityPlan2=bcpRepo.save(businessContinuityPlan);
		return businessContinuityPlan2;

	}

	@GetMapping(value = "/getplan/{serialNo}")
	public Optional<BusinessContinuityPlan> findbyAccountName(@PathVariable("serialNo") int serialNo) {
         Optional<BusinessContinuityPlan>bOptional=bcpRepo.findById(serialNo); 
		return bOptional;
	}

	@PostMapping(value = "/modifyplan/{serialNo}")
	public ResponseEntity<BusinessContinuityPlan> updateUser(@PathVariable("serialNo") int serialNo,
			@RequestBody BusinessContinuityPlan businessContinuityPlan) {
		     bcpRepo.save(businessContinuityPlan);

		return new ResponseEntity<BusinessContinuityPlan>(HttpStatus.OK);
	}

}
