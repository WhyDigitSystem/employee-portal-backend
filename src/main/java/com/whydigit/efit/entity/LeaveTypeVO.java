package com.whydigit.efit.entity;

import java.util.Date;

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
	
	private String leave_type;
	private int total_leave;
	private String createdby;
	private String updatedby;
	private boolean cancel;
	private boolean active;
	private String remarks;
	
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
	

}
