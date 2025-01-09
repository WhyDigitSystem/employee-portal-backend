package com.whydigit.efit.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "earnings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EarningsVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Double basicHRA;
	private Double specialAllowance;
	private Double projectAllowance;
	private Double incentives;
	private Double bonus;

	@OneToOne(mappedBy = "earningsVO", cascade = CascadeType.ALL)
	@JsonManagedReference
	private DeductionsVO deductionsVO;

}
