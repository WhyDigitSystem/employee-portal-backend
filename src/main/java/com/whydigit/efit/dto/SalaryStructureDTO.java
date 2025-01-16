package com.whydigit.efit.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.whydigit.efit.entity.SalaryStructureEarningsVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SalaryStructureDTO {

	private Long id;
    private String employeeCode;
    private String employeeName;
    private LocalDate dateOfBirth;
    private String grade;
    private String department;
    private String pan;
    private String bankAccountNo;
    private String position;
    private LocalDate dateOfJoining;
    private String createdBy;
    
    private List<SalaryStructureEarningsDTO> salaryStructureEarningsDTO;
	private List<SalaryStructureDeductionDTO> salaryStructureDeductionDTO;
}
