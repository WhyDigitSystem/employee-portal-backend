package com.whydigit.efit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whydigit.efit.entity.EmployeeDetailsVO;
import com.whydigit.efit.entity.LeaveTypeVO;


@Service
public interface BasicMasterService {

	


	List<EmployeeDetailsVO> getAllgetAllEmployees();

	Optional<EmployeeDetailsVO> getEmployeeById(int id);

	EmployeeDetailsVO createEmployee(EmployeeDetailsVO employeeVO);

	Optional<EmployeeDetailsVO> updateEmployee(EmployeeDetailsVO employeeVO);

	void deleteEmployee(int id);
	
	
	
	List<LeaveTypeVO> getAllgetAllLeaveType();

	Optional<LeaveTypeVO> getLeaveTypeById(int id);

	LeaveTypeVO createLeaveType(LeaveTypeVO leaveTypeVO);

	Optional<LeaveTypeVO> updateLeaveType(LeaveTypeVO leaveTypeVO);

	void deleteLeaveType(int id);


	





}
