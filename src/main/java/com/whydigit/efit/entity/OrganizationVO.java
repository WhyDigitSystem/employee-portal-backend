package com.whydigit.efit.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.whydigit.efit.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "organization")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String orgCode;
	private String founder;
	private String CEO;
	private String phoneNumber;
	private String address;
	private String PAN;
	private String GST;
	private String orgLogo;
	private boolean isActive;
	private long noOfLicence;
	private long orgId;
	private long branchId;
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "organizationVO")
	@JsonManagedReference
	private List<BranchVO> branchVO;
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
