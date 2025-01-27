package com.whydigit.efit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveDetailsDTO {

	private Long id;
	private String leaveType;
	private String leaveCode;
	private int noOfDays;
	private String effective;
	private Long orgId;
	private String createdBy;
	private boolean active;
	private String carryForward;
	private String leaveApplicable;

}
