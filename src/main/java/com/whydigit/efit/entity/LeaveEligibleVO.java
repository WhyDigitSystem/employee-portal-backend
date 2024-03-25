package com.whydigit.efit.entity;

import javax.persistence.Column;
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
@Table(name = "leaveeligible")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveEligibleVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "leaveeligibleid", length = 15)
	private Long id;

	@Column(name = "empcode", length = 15)
	private String empcode;

	@Column(name = "empname", length = 30)
	private String empname;

	@Column(name = "orgid", length = 15)
	private Long orgId;

	@Column(name = "branchid", length = 15)
	private Long branchid;

	@Column(name = "cancel", length = 15)
	private boolean cancel;

	@Column(name = "active", length = 15)
	private boolean active;

	@Column(name = "createdby", length = 30)
	private String createdby;

	@Column(name = "modifiedby", length = 30)
	private String modifiedby;

	@Column(name = "casual", length = 2)
	private int casual;

	@Column(name = "sick", length = 2)
	private int sick;

	@Column(name = "annual", length = 2)
	private int annual;

	@Column(name = "maternity", length = 2)
	private int maternity;

	@Column(name = "paternity", length = 2)
	private int paternity;

	@Column(name = "parental", length = 2)
	private int parental;

	@Column(name = "bereavement", length = 2)
	private int bereavement;

	@Column(name = "compensatory", length = 2)
	private int compensatory;

	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();
}
