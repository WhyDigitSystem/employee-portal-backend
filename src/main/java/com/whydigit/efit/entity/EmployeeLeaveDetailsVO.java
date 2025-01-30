package com.whydigit.efit.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.whydigit.efit.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employeeleavedetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLeaveDetailsVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "employeeleavedetailsgen")
	@SequenceGenerator(name = "employeeleavedetailsgen", sequenceName = "employeeleavedetailsseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "employeeleavedetailsid")
	private Long id;

	@Column(name = "clcount")
	private int cl;
	@Column(name = "ulcount")
	private int ul;
	@Column(name = "mlcount")
	private int ml;
	@Column(name = "plcount")
	private int pl;
	@Column(name = "alcount")
	private int al;
	@Column(name = "cocount")
	private int co;
	@Column(name = "slcount")
	private int sl;
	@Column(name ="orgid")
	private Long orgId;
	private String effective;
	@Column(name="carryforward")
	private String carryForward;
	
	private String gender;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="employeeid")
	private EmployeeVO employeeVO;

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
