package com.whydigit.efit.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetailsDTO {
	
	private long id;
	private long orgId;
	private String empCode;
	private String empName;
	private String gender;
	private LocalDate dateOfBirth;
	private String blood;
	private String department;
	private String designation;
	private String role;
	private String companyCode;
	private String branch;
	private String email;
	private LocalDate joiningDate;
	private LocalDate resigningDate;
	private String userType;
	private String mobileNo;
	private String alternateMobileNo;
	private String pan;
	private String aadhar;
	private String bankName;
	private long accountNo;
	private String ifscCode;
	private String reportingPerson;
	private String createdBy;
	private boolean active;
	private String grade;
	private String uan;
	private String remarks;
	private String reportingPersonRole;

}
