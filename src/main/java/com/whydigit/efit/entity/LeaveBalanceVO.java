package com.whydigit.efit.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="leavebalance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveBalanceVO {
	
	@Id
	private int id;
	private String empcode;
	private String empname;
	private String leavetype;
	private String availableleave;

}
