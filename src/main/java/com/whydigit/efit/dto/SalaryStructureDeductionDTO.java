package com.whydigit.efit.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryStructureDeductionDTO {
	
    private String heading;
    private BigDecimal amount;

}
