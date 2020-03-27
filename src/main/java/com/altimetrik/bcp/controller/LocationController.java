package com.altimetrik.bcp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.altimetrik.bcp.dao.AccountRepo;
import com.altimetrik.bcp.dao.LeaderRepo;
import com.altimetrik.bcp.dao.LocationRepo;
import com.altimetrik.bcp.dao.ProjectAssocRepo;
import com.altimetrik.bcp.entity.Leader;
import com.altimetrik.bcp.entity.Location;
import com.altimetrik.bcp.entity.ProjLocLeaderAssoc;

@RequestMapping("/location")
public class LocationController {

	@Autowired
	LocationRepo locationRepo;
	
	@Autowired
	AccountRepo acRepo;
	
	@GetMapping(value = "/getAllLocation")
	public ResponseEntity<List<Location>> getAllLocation(){
		List<Location> locationList = locationRepo.findAll();
		return ResponseEntity.ok().body(locationList);
	}
	
}
