package com.whydigit.efit.dto;

import java.time.LocalDate;
import java.util.List;

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
    private Long orgId;
    
    private List<SalaryStructureEarningsDTO> salaryStructureEarningsDTO;
	private List<SalaryStructureDeductionDTO> salaryStructureDeductionDTO;
}
