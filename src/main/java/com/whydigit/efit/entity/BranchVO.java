package com.whydigit.efit.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.whydigit.efit.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "branch")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String branchCode;
	private String branchManager;
	private String phoneNumber;
	private String address;
	private String PAN;
	private String GST;
	private boolean isActive;
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "org_id")
	private OrganizationVO organizationVO;
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
