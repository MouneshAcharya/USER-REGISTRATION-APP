package com.mouneshit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="CITIES_MASTER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitiesMasterEntity {
	@Id
	@Column(name="CITY_ID")
	private Integer cityId;
	@Column(name="CITY_NAME")
	private String cityName;
	@Column(name="STATE_ID")
	private Integer stateId;
}
