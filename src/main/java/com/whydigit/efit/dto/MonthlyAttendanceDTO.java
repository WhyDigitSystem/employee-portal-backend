package com.whydigit.efit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyAttendanceDTO {

private Long orgId;
	
	private String branch;
	
	private String branchCode;
	
	private String empCode;
	
	private String empName;
	
	private String salaryMonth;
	
	private float totalDays;
	
	private float totalLeaves;
	
	private float precentDays;
	
	private float lop;
	
	private String year;
	
}
