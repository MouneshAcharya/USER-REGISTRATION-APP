package com.mouneshit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mouneshit.service.UserManagement;

@RestController
public class ForgotPasswordRestController {
	
	@Autowired
	private UserManagement service;
	
	
	@GetMapping("/forgotPwd/{email}")
	public String forgotPwd(@PathVariable String email) {
		return service.forgotPassword(email);
	}

}
