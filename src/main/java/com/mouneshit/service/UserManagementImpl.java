package com.mouneshit.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.omg.CORBA.UserException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouneshit.binding.LoginForm;
import com.mouneshit.binding.UnlockForm;
import com.mouneshit.binding.UserForm;
import com.mouneshit.entity.CitiesMasterEntity;
import com.mouneshit.entity.CountryMasterEntity;
import com.mouneshit.entity.StateMasterEntity;
import com.mouneshit.entity.UserAccountEntity;
import com.mouneshit.repository.CityRepository;
import com.mouneshit.repository.CountryRespository;
import com.mouneshit.repository.StateRepository;
import com.mouneshit.repository.UserRepositoty;
import com.mouneshit.utils.EmailUtils;

@Service
public class UserManagementImpl implements UserManagement {
	
	@Autowired
	private UserRepositoty userRepo;
	
	@Autowired
	private CountryRespository countryRepo;
	
	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private CityRepository cityRepo;
	
	@Autowired
	private EmailUtils emailUtils;
	
	private String generateRandomPassword() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 6;
	    Random random = new Random();

	    String generatedPwd = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    return generatedPwd;
	}
	
	private String readMailBodyContent(String fileName,UserAccountEntity entity) {
		String mailBody = null;
		try {
			StringBuffer sb = new StringBuffer();
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while(line != null) {
				sb.append(line);
				line = br.readLine();
			}
			mailBody = sb.toString();
			mailBody = mailBody.replace("{FNAME}", entity.getFname());
			mailBody = mailBody.replace("{LNAME}", entity.getLname());
			mailBody = mailBody.replace("{TEMP-PWD}", entity.getPassword());
			mailBody = mailBody.replace("{EMAIL}", entity.getEmail());
			mailBody = mailBody.replace("{PWD}", entity.getPassword());
			br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

	 

	@Override
	public String login(LoginForm loginForm) {
		UserAccountEntity userAcc = userRepo.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
		if(userAcc == null) {
			return "Invalid Credentials";
		}
		if(userAcc != null && userAcc.getAccStatus().equals("LOCKED")) {
			return "Your Account Locked.";
		}
		return "SUCCESS";
	}

	@Override
	public String emailCheck(String email) {
		UserAccountEntity userAcc = userRepo.findByEmail(email);
		if(userAcc == null) {
			return "UNIQUE";
		}
		return "DUPLICATE";
	}

	@Override
	public Map<Integer, String> loadCountries() {
		List<CountryMasterEntity> contries = countryRepo.findAll();
		Map<Integer,String> countryMap = new HashMap<>();
		for(CountryMasterEntity country : contries) {
			countryMap.put(country.getCountryId(),country.getCountryName());
		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadState(Integer countryId) {
		List<StateMasterEntity> states = stateRepo.findByCountryId(countryId);
		Map<Integer,String> stateMap = new HashMap<>();
		for(StateMasterEntity state:states) {
			stateMap.put(state.getStateId(),state.getStateName());
		}
		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCities(Integer stateId) {
		List<CitiesMasterEntity> cities = cityRepo.findByStateId(stateId);
		Map<Integer,String> cityMap = new HashMap<>();
		for(CitiesMasterEntity city : cities) {
			cityMap.put(city.getCityId(),city.getCityName());
		}
		return cityMap;
	}

	@Override
	public String registerUser(UserForm userForm) {
		UserAccountEntity entity = new UserAccountEntity();
		BeanUtils.copyProperties(userForm, entity);
		entity.setAccStatus("LOCKED");
		String randomPwd = generateRandomPassword();
		entity.setPassword(randomPwd);
		userRepo.save(entity);
		String email = userForm.getEmail();
		String subject = "User Registration Mounesh IT";
		String fileName = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt";
		String body = readMailBodyContent(fileName,entity);
		boolean isSent = emailUtils.sendEmail(email, subject, body);
		if(isSent) {
			return "SUCCESS";
		}
		return "FAILURE";
	}

	@Override
	public String unlockUser(UnlockForm unlockForm) {
		if(!unlockForm.getNewPwd().equals(unlockForm.getConfirmNewPwd())) {
			return "Password and confirm password should be same";
		}
		UserAccountEntity userAcc = userRepo.findByEmailAndPassword(unlockForm.getEmail(), unlockForm.getTempPwd());
		if(userAcc == null) {
			return "Incorrect temparory password";
		}
		userAcc.setAccStatus("UNLOCKED");
		userAcc.setPassword(unlockForm.getNewPwd());
		userRepo.save(userAcc);
		return "Account unlocked";
	}

	@Override
	public String forgotPassword(String email) {
		UserAccountEntity userAcc = userRepo.findByEmail(email);
		if(userAcc == null) {
			return "Invalid mail id";
		}
		String fileName = "RECOVER-PASSWORD-EMAIL-BODY-TEMPLATE.txt";
		String body = readMailBodyContent(fileName,userAcc);
		String subject = "Recover Password Mounesh IT";
		boolean isSent = emailUtils.sendEmail(email, subject, body);
		if(isSent) {
			return "Password sent to registered email";
		}
		return "ERROR";
	}

}
