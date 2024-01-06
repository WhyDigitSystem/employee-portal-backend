package com.whydigit.efit.entity;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.whydigit.efit.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permissionrequest")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PermissionRequestVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date permissiondate;
	private String fromhour;
	private String tohour;
	private int totalhours;
	private String notes;
	private String username;
	private String createdby;
	private String updatedby;
	private String approvedby;
	private String status;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date approvedat;
	private boolean cancel;
	private boolean active;
	private String remarks;
	
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}