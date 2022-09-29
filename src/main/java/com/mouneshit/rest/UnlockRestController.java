package com.mouneshit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mouneshit.binding.UnlockForm;
import com.mouneshit.service.UserManagement;

@RestController
public class UnlockRestController {
	
	@Autowired
	private UserManagement service;
	
	@PostMapping("/unlock")
	public String unlockUser(@RequestBody UnlockForm form) {
		return service.unlockUser(form);
	}

}
