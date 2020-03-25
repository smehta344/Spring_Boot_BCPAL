package com.altimetrik.bcp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping(value = "/login/{userName}/{password}")
	public String loginUser(@PathVariable("userName") String userName, @PathVariable("password") String password) {
		if (userName.equals("Sanvjeev") && (password.equals("1234567"))) {
			return "Login";

		}
		return "invalid Credential";

	}

}
