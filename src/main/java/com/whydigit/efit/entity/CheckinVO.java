package com.whydigit.efit.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="checkin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckinVO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String empcode;
	private String companycode;
	private String branch;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date checkin_date=new Date();
    private Date entry_time;
    private String status;
    private long orgId;
	private long branchId;
	

}
