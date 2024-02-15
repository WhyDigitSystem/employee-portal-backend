package com.whydigit.efit.controller;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {
	@NotNull(message = "Organization id is required")
	private Long orgId;
	private Long id;
	private String branchCode;
	private String branchManager;
	private String phoneNumber;
	private String address;
	private String PAN;
	private String GST;
	private boolean isActive;

}
