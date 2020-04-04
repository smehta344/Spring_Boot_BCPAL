package com.altimetrik.bcp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.bcp.model.LoginService;

@RestController
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login")
	public ResponseEntity<?> getLogin(@RequestBody LoginService ser) {
		logger.info("Login sucessfully");
		return ResponseEntity.ok("suss" + ser.getUsername());
	}
}
