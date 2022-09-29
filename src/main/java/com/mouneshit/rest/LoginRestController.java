package com.mouneshit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mouneshit.binding.LoginForm;
import com.mouneshit.service.UserManagement;

@RestController
public class LoginRestController {
	
	@Autowired
	private UserManagement service;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm form) {
		return service.login(form);
	}

}
