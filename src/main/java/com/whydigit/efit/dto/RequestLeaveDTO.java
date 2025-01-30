package com.whydigit.efit.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLeaveDTO {

	private Long id;

	private String leaveType;

	private String leaveCode;

	private String notify;

	private LocalDate fromDate;

	private LocalDate toDate;

	private int totalLeave;

	private String remarks;

	private String createdBy;

	private boolean active;
	
	private Long empId;
	
	private Long orgId;
	
	private String dayType;

}
