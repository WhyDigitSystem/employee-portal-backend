package com.whydigit.efit.entity;

import java.time.LocalDate;

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
@Table(name = "holidays")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class HolidayVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long orgId;
	private String companycode;
	private String branchId;
	private String department;
	private LocalDate holiday_date;
	private String day;
	private String festival;
	private String createdby;
	private String updatedby;
	private boolean cancel;
	private boolean active;
	private String remarks;
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
