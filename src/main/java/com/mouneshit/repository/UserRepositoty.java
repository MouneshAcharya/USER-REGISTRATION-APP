package com.mouneshit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mouneshit.entity.UserAccountEntity;

public interface UserRepositoty extends JpaRepository<UserAccountEntity, Integer> {
	
	public UserAccountEntity findByEmailAndPassword(String email, String Password);

	
	public UserAccountEntity findByEmail(String email);
}
