package com.mouneshit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="COUNTRY_MASTER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryMasterEntity {
	@Id
	@Column(name ="COUNTRY_ID")
	private Integer countryId;
	@Column(name = "COUNTRY_NAME")
	private String countryName;
}
