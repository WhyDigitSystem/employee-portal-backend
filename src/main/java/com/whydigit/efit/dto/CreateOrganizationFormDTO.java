package com.whydigit.efit.dto;

//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrganizationFormDTO {
	@NotBlank(message = "Employee code is required")
	private String empCode;
	
	@NotBlank(message = "Employee name is required")
	private String empName;

	@NotBlank(message = "Email is required")
	@Size(max = 30)
	@Email
	private String email;
	
	private OwnerAccessRole role;
	
	private long orgId;

	@NotBlank
	@Size(min = 6, max = 100, message = "Password is required")
	private String password;

	private OrganizationDTO organizationDTO; 
}