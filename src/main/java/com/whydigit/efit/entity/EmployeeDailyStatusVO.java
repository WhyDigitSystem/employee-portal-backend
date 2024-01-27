package com.whydigit.efit.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee_daily_status")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeDailyStatusVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long empId;
	private long orgId;	
	private LocalDateTime loginDate;
	private LocalDateTime logoutDate;
	private LocalDateTime actionAt;
	private boolean isCheckIn;
	

}
