package com.whydigit.efit.entity;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="perminissionreport")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionReportVO {
	
	@Id
	private int id;
	private LocalDate permissiondate;
	private String companycode;
	private String branch;
	@DateTimeFormat(pattern = "HH:mm")
	private String  fromhour;
	@DateTimeFormat(pattern = "HH:mm")
	private String  tohour;
	@DateTimeFormat(pattern = "HH:mm")
	private String  totalhours;
	private String notes;
	private String empcode;
	private String empname;
	private String createdby;
	private String updatedby;
	private String approvedby;
	private String status;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date approvedat;
	private boolean cancel;
	private boolean active;
	private String remarks;
	private Date createdDate;
	private Date updatedDate;

}
