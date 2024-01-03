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
@Table(name = "companySet")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanySetVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String CompanyCode;
	private String address;
    private String zip;
	private String town;
	private String country;
    private String state;
	private String mainCurrency;
	private String phone;
    private String email;
	private String webSite;
	private String note;
    private String image;
	private String directorName;
	private String capital;
    private String entityType;
	private String TIN;
	private String PAN;
    private String companyObject;
    @Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
