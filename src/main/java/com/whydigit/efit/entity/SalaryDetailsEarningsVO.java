package com.whydigit.efit.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="salarydetailsearnings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryDetailsEarningsVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salarydetailsearningssgen")
	@SequenceGenerator(name = "salarydetailsearningssgen", sequenceName = "salarydetailsearningsseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "salarydetailsearningsid")
	private Long id;
	
	@Column(name = "heading", length = 25)
    private String heading;

    @Column(name = "amount", precision = 10,scale = 2) 
    private BigDecimal amount;
    
	@ManyToOne
	@JoinColumn(name = "salaryprocessid")
	@JsonBackReference
	private SalaryProcessVO salaryProcessVO;

}
