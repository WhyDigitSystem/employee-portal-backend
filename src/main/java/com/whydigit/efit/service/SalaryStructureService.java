package com.whydigit.efit.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.SalaryStructureDTO;
import com.whydigit.efit.entity.SalaryMasterVO;
import com.whydigit.efit.entity.SalaryStructureVO;
import com.whydigit.efit.exception.ApplicationException;

@Service
public interface SalaryStructureService {
	
	List<Map<String,Object>> getEmployeeNameDetails(Long orgId,String month,String year);

	Map<String, Object> createUpdateSalaryStructure(SalaryStructureDTO salaryStructureDTO) throws ApplicationException;
	
	List<SalaryMasterVO>getAllSalaryMasterForSalaryStructure(Long orgId);
	
	List<SalaryStructureVO>getAllSalaryStructures(Long orgId);
	
	SalaryStructureVO getSalaryStructureById(Long orgId);

	SalaryStructureVO getEmployeeSalaryPDF(Long orgId, String empCode, String month, String year);

}
