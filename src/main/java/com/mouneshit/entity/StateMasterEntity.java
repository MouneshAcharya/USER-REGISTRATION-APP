package com.mouneshit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="STATES_MASTER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateMasterEntity {
	@Id
	@Column(name="STATE_ID")
	private Integer stateId;
	@Column(name="STATE_NAME")
	private String stateName;
	@Column(name="COUNTRY_ID")
	private Integer countryId;

}
