package com.mouneshit.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mouneshit.binding.UserForm;
import com.mouneshit.service.UserManagement;

@RestController
public class UserRegRestController {
	
	@Autowired
	private UserManagement service;
	
	@GetMapping("/email/{emailId}")
	public String emailCheck(@PathVariable String emailId) {
		return service.emailCheck(emailId);
	}
	
	@GetMapping("/countries")
	public Map<Integer, String> loadCountries(){
		return service.loadCountries();
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer,String> loadStates(@PathVariable Integer countryId){
		return service.loadState(countryId);
	}
	
	@GetMapping("/cities/{stateId}")
	public Map<Integer,String> loadCities(@PathVariable Integer stateId){
		return service.loadCities(stateId);
	}
	
	@PostMapping("/register")
	public String registerUser(@RequestBody UserForm userForm) {
		return service.registerUser(userForm);
	}
	

}
