package com.whydigit.efit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employeetodaystatus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCheckinDailyStatusVO {
	
	@Id
	private String empcode;
	private String empname;
	private LocalDate entrydate;
	private String entrytime;
	private String status;
	

}
