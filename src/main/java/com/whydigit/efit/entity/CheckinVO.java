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
@Table(name="checkintime")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckinVO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	private String userid;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date checkin=new Date();
    private Date checkInTime;
    private Date checkOutTime;
	

}
