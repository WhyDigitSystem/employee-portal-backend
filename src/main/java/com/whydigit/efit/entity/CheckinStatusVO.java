package com.whydigit.efit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="checkinstatus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckinStatusVO {
	
	@Id
	private String empcode;
	private String status;
}
