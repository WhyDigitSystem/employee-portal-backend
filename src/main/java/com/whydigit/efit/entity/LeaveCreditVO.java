package com.whydigit.efit.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.whydigit.efit.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="leave_credit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveCreditVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String empcode;
	private String leavetype;
	private String leavecount;
	private String companycode;
	private String branch;
	private String createdby;
	private String updatedby;
	private boolean cancel;
	private boolean active;
	private String remarks;
	private long orgId;
	private long branchId;
	
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
