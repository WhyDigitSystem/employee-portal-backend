package com.whydigit.efit.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.whydigit.efit.dto.CreatedUpdatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="salarystructure")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryStructureVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salarystructuregen")
	@SequenceGenerator(name = "salarystructuregen", sequenceName = "salarystructureseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "salarystructureid")
	private Long id;
	
	@Column(name = "employeecode", length = 25)
    private String employeeCode;

    @Column(name = "employeename", length = 100) 
    private String employeeName;

    @Column(name = "dateofbirth") 
    private LocalDate dateOfBirth;

    @Column(name = "garde", length = 30)
    private String grade;

    @Column(name = "department", length = 30)
    private String department;

    @Column(name = "pan", length = 50)
    private String pan;

    @Column(name = "bankaccountno", length = 50)
    private String bankAccountNo;

    @Column(name = "position", length = 50)
    private String position;

    @Column(name = "dateofjoining") 
    private LocalDate dateOfJoining;
    
    @Column(name = "createdby", length = 50)
    private String createdBy;
    
    @Column(name = "modifiedby", length = 50)
    private String modifiedBy;

    
    @OneToMany(mappedBy = "salaryStructureVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalaryStructureEarningsVO> salaryStructureEarningsVO;
    
    @OneToMany(mappedBy = "salaryStructureVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalaryStructureDeductionVO> salaryStructureDeductionVO;
	
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
