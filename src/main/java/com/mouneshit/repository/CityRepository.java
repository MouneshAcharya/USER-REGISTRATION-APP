package com.mouneshit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mouneshit.entity.CitiesMasterEntity;

public interface CityRepository extends JpaRepository<CitiesMasterEntity, Integer> {
	public List<CitiesMasterEntity> findByStateId(Integer stateId); 
}
