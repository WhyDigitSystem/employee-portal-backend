package com.whydigit.efit.entity;

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
@Table(name = "monthlyattendance")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyAttendanceVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "monthlyattendancegen")
	@SequenceGenerator(name = "monthlyattendancegen", sequenceName = "monthlyattendanceseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "monthlyattendanceid")
	private Long id;
	@Column(name="orgid")
	private Long orgId;
	private String branch;
	@Column(name="branchcode")
	private String branchCode;
	@Column(name="empcode")
	private String empCode;
	@Column(name="empname")
	private String empName;
	@Column(name="salarymonth")
	private String salaryMonth;
	@Column(name="totaldays")
	private float totalDays;
	@Column(name="totalleaves")
	private float totalLeaves;
	@Column(name="presentdays")
	private float presentDays;
	@Column(name="lop")
	private float lop;
	@Column(name="year")
	private String year;
	
	
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
	
	
}
