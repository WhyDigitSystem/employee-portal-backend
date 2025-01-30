package com.whydigit.efit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.whydigit.efit.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "requestleave")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLeaveVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "requestleavegen")
	@SequenceGenerator(name = "requestleavegen", sequenceName = "requestleaveseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "requestleaveid")
	private Long id;
	@Column(name = "leavetype")
	private String leaveType;
	@Column(name = "leavecode")
	private String leaveCode;
	private String notify;
	@Column(name = "fromdate")
	private LocalDate fromDate;
	@Column(name = "todate")
	private LocalDate toDate;
	@Column(name = "totalleave")
	private int totalLeave;
	private String remarks;
	@Column(name = "createdby")
	private String createdBy;
	@Column(name = "modifiedBy")
	private String updatedBy;
	@Column(name = "empid")
	private Long empId;
	@Column(name = "orgid")
	private Long orgId;
	private boolean cancel;
	private boolean active;
	@Column(name="daytype")
	private String dayType;
	
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
