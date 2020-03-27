package com.altimetrik.bcp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.altimetrik.bcp.dao.LeaderRepo;
import com.altimetrik.bcp.dao.ProjectAssocRepo;
import com.altimetrik.bcp.entity.Leader;
import com.altimetrik.bcp.entity.ProjLocLeaderAssoc;

@RequestMapping("/location")
public class LeaderController {

	@Autowired
	LeaderRepo leaderRepo;
	
	@Autowired
	ProjectAssocRepo asscoRepo;
	
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
	
}
