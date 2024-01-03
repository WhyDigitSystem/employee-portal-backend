package com.whydigit.efit.entity;

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
@Table(name = "bs_company_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyInfoVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String companyName;
	private String city;
    private String address;
    private String manager;
    private String contactno;
    private String tenantId;
    @Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
