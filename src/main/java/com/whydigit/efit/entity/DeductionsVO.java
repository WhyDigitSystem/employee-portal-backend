package com.whydigit.efit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deduction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeductionsVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Double professionalTax;
	private Double pf;
	private Double vpf;
	private Double medicalclaim;

	@OneToOne
	@JsonBackReference
	@JoinColumn(name = "earningsid", referencedColumnName = "id")
	private EarningsVO earningsVO;
}
