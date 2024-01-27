package com.whydigit.efit.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeInOutActionDTO {
	@NotNull(message = "EmployeeId is Required")
	private Long empId;
	@NotNull(message = "OrganizationId is Required")
	private Long orgId;
	private boolean isCheckIn;
}
