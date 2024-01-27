package com.whydigit.efit.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LeaveApprovalDTO {
	
	private int requestid;
	private String status;
	private String approvedby;
	private String remarks;
	private Date approvedat;
}
