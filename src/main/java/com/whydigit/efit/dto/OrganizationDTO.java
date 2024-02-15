package com.whydigit.efit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDTO {

	private Long id;
	private String name;
	private String orgCode;
	private String founder;
	private String CEO;
	private String phoneNumber;
	private String address;
	private String PAN;
	private String GST;
	private String orgLogo;
	private boolean isActive;
}
