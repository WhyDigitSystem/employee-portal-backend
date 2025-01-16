package com.whydigit.efit.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.whydigit.efit.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="salarymaster")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryMasterVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String headings;
	private String code;
	private String category;
	private String type;
	private Double amount;
	private boolean active;
	private Long orgid;
	
	@Embedded
	private CreatedUpdatedDate createdUpdatedDate= new CreatedUpdatedDate();

}
