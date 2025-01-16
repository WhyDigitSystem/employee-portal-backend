package com.whydigit.efit.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.SalaryStructureDTO;
import com.whydigit.efit.entity.EmployeeDetailsVO;
import com.whydigit.efit.entity.SalaryMasterVO;
import com.whydigit.efit.exception.ApplicationException;

@Service
public interface SalaryStructureService {
	
	List<EmployeeDetailsVO> getEmployeeNameDetails(Long orgId);

	Map<String, Object> createUpdateSalaryStructure(SalaryStructureDTO salaryStructureDTO) throws ApplicationException;
	
	List<SalaryMasterVO>getAllSalaryMasterForSalaryStructure(Long orgId);

}
