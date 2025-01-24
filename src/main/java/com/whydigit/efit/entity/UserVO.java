package com.whydigit.efit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.whydigit.efit.dto.CreatedUpdatedDate;
import com.whydigit.efit.dto.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String empname;
	private String email;
	private String empcode;
	private long empId;
	private String password;
	private String profileImage;
	private boolean loginStatus;
	private boolean isActive;
	private Long branchId;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(name = "orgId")
	private Long orgId;
	
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

	private Date accountRemovedDate;
}
