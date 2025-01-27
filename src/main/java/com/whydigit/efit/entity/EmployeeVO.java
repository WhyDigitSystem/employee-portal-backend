package com.whydigit.efit.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.whydigit.efit.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "leavedetailsgen")
	@SequenceGenerator(name = "leavedetailsgen", sequenceName = "leavedetailsseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "employeeid")
	private Long id;

	@Column(name = "orgid")
	private long orgId;
	@Column(name = "empcode")
	private String empCode;
	@Column(name = "empname")
	private String empName;
	private String gender;
	@Column(name = "dateofbirth")
	private LocalDate dateOfBirth;
	private String blood;
	private String department;
	private String designation;
	private String role;
	@Column(name = "companycode")
	private String companyCode;
	@Column(name = "branchid")
	private String branchId;
	private String email;
	@Column(name = "joiningdate")
	private LocalDate joiningDate;
	@Column(name = "resigningdate")
	private LocalDate resigningDate;
	@Column(name = "usertype")
	private String userType;
	@Column(name = "mobileno")
	private String mobileNo;
	private String pan;
	private String aadhar;
	@Column(name = "bankname")
	private String bankName;
	@Column(name = "accountno")
	private long accountNo;
	@Column(name = "ifsccode")
	private String ifscCode;
	@Column(name = "reportingperson")
	private String reportingPerson;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modyfiedby")
	private String updatedby;
	private boolean cancel;
	private boolean active;
	private String remarks;
	@Column(name = "reportingpersonrole")
	private String reportingPersonRole;
	
	@Column(name="alternatemobileno")
	private String alternateMobileNo;
	
	@OneToMany(mappedBy ="employeeVO",cascade =CascadeType.ALL)
	@JsonManagedReference
	private List<EmployeeLeaveDetailsVO> employeeLeaveDetailsVO;

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
