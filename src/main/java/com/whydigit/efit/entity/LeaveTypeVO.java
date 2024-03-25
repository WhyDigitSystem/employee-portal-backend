package com.whydigit.efit.entity;

import javax.persistence.Column;
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
@Table(name = "leave_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveTypeVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String companycode;
	private String branchId;
	private String leave_type;
	private String leave_code;
	private String applicable;
	private int total_leave;
	private String createdby;
	private String updatedby;
	@Column(name = "effective", length = 15)
	private String effective;
	private boolean cancel;
	private boolean active;
	private String remarks;
	private long orgId;

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
