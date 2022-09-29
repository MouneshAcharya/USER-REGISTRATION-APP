package com.mouneshit.binding;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class UserForm {
	
	private String fname;
	private String lname;
	private String email;
	private Long phno;
	private String gender;
	@DateTimeFormat(pattern = "dd-MMM-yyyy")
	private LocalDate dob;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	
}
