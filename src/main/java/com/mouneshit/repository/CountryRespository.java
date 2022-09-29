package com.mouneshit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mouneshit.entity.CountryMasterEntity;

public interface CountryRespository extends JpaRepository<CountryMasterEntity, Integer> {
	
}
