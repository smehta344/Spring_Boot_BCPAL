package com.altimetrik.bcp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.altimetrik.bcp.dao.AccountRepo;
import com.altimetrik.bcp.dao.LeaderRepo;
import com.altimetrik.bcp.dao.ProjectAssocRepo;
import com.altimetrik.bcp.entity.Account;
import com.altimetrik.bcp.entity.Leader;
import com.altimetrik.bcp.entity.Location;
import com.altimetrik.bcp.entity.ProjLocLeaderAssoc;

@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountRepo accountRepo;
	
	@GetMapping(value = "/getAllAccounts")
	public ResponseEntity<List<Account>> getAllLocation(){
		List<Account> accountList = accountRepo.findAll();
		return ResponseEntity.ok().body(accountList);
	}
	
}
