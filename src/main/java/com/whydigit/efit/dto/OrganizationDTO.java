package com.whydigit.efit.dto;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {

	@NotBlank(message = "Organization name is required")
	private String name;
	private String phoneNumber;
	private String street;
	private String city;
	private String state;
	private String postalCode;
	private String country;
	private String orgLogo;
 	
}
