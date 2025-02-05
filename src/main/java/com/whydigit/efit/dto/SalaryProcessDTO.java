package com.whydigit.efit.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryProcessDTO {
	
	private Long orgId;
	private String empCode;
	private String empName;
	private int monthDays;
	private float totalLeaves;
	private float lop;
	private float effectiveWorkingDays;
	private String year;
	private String month;
	private BigDecimal grossPay;
	private BigDecimal perDaySalary;
	private BigDecimal netPay;
	private BigDecimal lopAmount;

}
