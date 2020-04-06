package com.altimetrik.bcp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.bcp.model.LoginService;

@RestController
public class LoginController {

	@RequestMapping(value = "/login")
	public ResponseEntity<?> getLogin(@RequestBody LoginService ser) {
		return ResponseEntity.ok("suss" + ser.getUsername());
	}
}
