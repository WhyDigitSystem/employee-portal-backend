package com.whydigit.efit.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.EmployeeDTO;
import com.whydigit.efit.dto.LeaveDetailsDTO;
import com.whydigit.efit.entity.EmployeeVO;
import com.whydigit.efit.entity.LeaveDetailsVO;
import com.whydigit.efit.exception.ApplicationException;

@Service
public interface MasterControllerService {

	//LeaveDeatils
	
	Map<String, Object> createUpdateLeaveDeatils(LeaveDetailsDTO leaveDetailsDTO) throws ApplicationException;

	List<LeaveDetailsVO> getAllLeaveDetails(Long orgId);

	Optional<LeaveDetailsVO> getAllLeaveDetailsById(Long id);
	
	List<Map<String, Object>> getLeaveCode(Long orgId);

	

	//NEW EMPLOYEE CREATION
	
	Map<String, Object> createUpdateEmployee(EmployeeDTO employeeDTO) throws ApplicationException;

	Optional<EmployeeVO> getEmployeeById(Long id);

	List<EmployeeVO> getAllEmployee(Long orgId);

	List<Map<String, Object>> getEmployeeLeaveCount(Long orgId, Long empId);


	
}
