package com.whydigit.efit.entity;

import java.math.BigDecimal;
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
@Table(name="salaryprocess")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryProcessVO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salaryprocessgen")
	@SequenceGenerator(name = "salaryprocessgen", sequenceName = "salaryprocessseq", initialValue = 1000000001, allocationSize = 1)
	@Column(name = "salaryprocessid")
	private Long id;
	
	@Column(name = "orgid")
	private Long orgId;
	
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
    
    @Column(name = "totaldays", length = 50)
    private int totalDays;
    
    @Column(name = "totalleaves")
    private float totalLeaves;
    
    @Column(name = "effectiveworkingdays")
    private float effectiveWorkingDays;

    @Column(name = "dateofjoining") 
    private LocalDate dateOfJoining;
    
    @Column(name = "createdby", length = 50)
    private String createdBy;
    
    @Column(name = "totalearnings")
    private BigDecimal totalEarnings;
    
    @Column(name = "totaldeduction")
    private BigDecimal totalDeduction;
    
    @Column(name = "netpay")
    private BigDecimal netPay;
    
    @Column(name = "grosspay")
    private BigDecimal grossPay;
    
    @Column(name = "perdaysalary")
    private BigDecimal perDaySalary;
    
    @Column(name = "lopamount")
	private BigDecimal lopAmount;
    
    @Column(name = "amountinwords")
    private String amountInWords;
    
    @Column(name = "modifiedby", length = 50)
    private String modifiedBy;
    
    @Column(name = "bankname", length = 100)
    private String bankName;
    @Column(name = "lop")
    private float lop;
    @Column(name = "uan")
    private String uan;
    @Column(name = "location")
    private String location;
    
    @Column(name = "salarymonth")
    private String month;
    
    @Column(name = "year", length = 30)
    private String year;
    

    
    @OneToMany(mappedBy = "salaryProcessVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalaryDetailsEarningsVO> salaryDetailsEarningsVO;
    
    @OneToMany(mappedBy = "salaryProcessVO",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<SalaryDetailsDeductionVO> salaryDetailsDeductionVO;
	
	@Embedded
	private CreatedUpdatedDate commonDate = new CreatedUpdatedDate();

}
