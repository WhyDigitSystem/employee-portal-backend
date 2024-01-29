package com.whydigit.efit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInOutActionDTO {
	private Long empId;
	private Long orgId;
	private boolean isCheckIn;
}
