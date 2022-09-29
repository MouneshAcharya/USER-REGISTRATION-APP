package com.mouneshit.service;

import java.util.Map;

import com.mouneshit.binding.LoginForm;
import com.mouneshit.binding.UnlockForm;
import com.mouneshit.binding.UserForm;

public interface UserManagement {
	
	public String login(LoginForm loginForm);
	
	public String emailCheck(String email);
	
	public Map<Integer,String> loadCountries();
	
	public Map<Integer,String> loadState(Integer countryId);
	
	public Map<Integer,String> loadCities(Integer stateId);
	
	public String registerUser(UserForm userForm);
	
	public String unlockUser(UnlockForm unlockForm);
	
	public String forgotPassword(String email);

}
