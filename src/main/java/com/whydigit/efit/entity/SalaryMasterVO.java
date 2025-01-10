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
@Table(name = "salarymaster")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryMasterVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Double headings;
	private Double code;
	private Double category;
	private Double type;
	private Double amount;
	private boolean active;

}
