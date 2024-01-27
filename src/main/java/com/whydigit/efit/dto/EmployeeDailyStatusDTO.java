package com.whydigit.efit.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDailyStatusDTO {

	private long empId;
	private String empName;
	private long orgId;
	private LocalDateTime loginDate;
	private LocalDateTime logoutDate;
	private boolean isCheckIn;

}
