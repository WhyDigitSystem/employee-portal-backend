package com.whydigit.efit.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whydigit.efit.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long orgId;
	private String empcode;
	private String empname;
	private String gender;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate date_of_birth;
	private String blood;
	private String department;
	private String designation;
	private String role;
	private String companycode;
	private String branchId;
	private String email;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate joining_date;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate resigning_date;
	private String user_type;
	private String mobile_no;
	private String alternate_mobile_no;
	private String pan;
	private String aadhar;
	private String bank_name;
	private long account_no;
	private String ifsc_code;
	private String reporting_person;
	private String createdby;
	private String updatedby;
	private boolean cancel;
	private boolean active;
	private String remarks;
	private String reporting_person_role;
	
	
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
	
	

}
