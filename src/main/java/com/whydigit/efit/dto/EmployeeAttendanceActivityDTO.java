package com.whydigit.efit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeAttendanceActivityDTO {
    
	private String empCode;
	private String empName;
	private long workingDays;
	private long noOfWeekendDays ;
	private long noOfCommonHolidays;
	private long noOfDepHolidays;	
	private long totalHolidays;
	private long totalDays;	
	private long compTaken;
	private float numberOfLeaveDays;
	private long presentDays;
	
}
