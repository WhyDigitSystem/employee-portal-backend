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

	@NotBlank(message = "Organization Name is required")
	private String orgName;

	@NotBlank(message = "Email is required")
	@Size(max = 30)
	@Email
	private String email;
		
	@NotBlank
	@Size(min = 6, max = 100, message = "Password is required")
	private String password;
	
	private long noOfLicence;
}
