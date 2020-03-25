package com.altimetrik.bcp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.bcp.model.BusinessContinuityPlan;

@RestController
public class BCMController {
	@PostMapping("/addplan")
	public BusinessContinuityPlan createUser(@RequestBody BusinessContinuityPlan businessContinuityPlan) {

		return new BusinessContinuityPlan();

	}

	@GetMapping(value = "/getplan/{accountName}")
	public BusinessContinuityPlan findbyAccountName(@PathVariable("accountName") String accountName) {

		return new BusinessContinuityPlan();
	}

	@PutMapping(value = "/modifyplan/{accountName}")
	public ResponseEntity<BusinessContinuityPlan> updateUser(@PathVariable("accountName") String accountName,
			@RequestBody BusinessContinuityPlan businessContinuityPlan) {

		return new ResponseEntity<BusinessContinuityPlan>(HttpStatus.OK);
	}

}
