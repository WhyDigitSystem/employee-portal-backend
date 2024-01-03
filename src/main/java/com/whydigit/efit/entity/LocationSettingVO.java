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
@Table(name = "bs_locationsettings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationSettingVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String warehousename;
	private String reservoirname;
	private String reservoircategory;
	private String locationcode;
	private Float locationlength;
	private Float locationwidth;
	private Float locationheight;
	private Float locationvolume;
	private Float locationload;
	private Integer roadwayno;
	private Integer shelfno;
	private Integer layerno;
	private Integer tagno;
	private Boolean active;
    @Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
