package com.whydigit.efit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="attendance_time")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCheckInTimeVO {
	@Id
	private int id;
	private String empcode;
	private LocalDate entry_date;
	private String checkintime;
	private String checkouttime;
	
	

}
