package com.whydigit.efit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whydigit.efit.entity.EmployeeDetailsVO;
import com.whydigit.efit.entity.LeaveTypeVO;
import com.whydigit.efit.repo.EmployeeDetailsRepo;
import com.whydigit.efit.repo.LeaveTypeRepo;



@Service
public class BasicMasterServiceImpl implements BasicMasterService {

	@Autowired
	private EmployeeDetailsRepo employeeRepo;
	
	@Autowired
	private LeaveTypeRepo leaveTypeRepo;

	
//	employee

	@Override
	public List<EmployeeDetailsVO> getAllgetAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Optional<EmployeeDetailsVO> getEmployeeById(int id) {
		return employeeRepo.findById(id);
	}

	@Override
	public EmployeeDetailsVO createEmployee(EmployeeDetailsVO employeeVO) {
		return employeeRepo.save(employeeVO);
	}

	@Override
	public Optional<EmployeeDetailsVO> updateEmployee(EmployeeDetailsVO employeeVO) {
		if (employeeRepo.existsById(employeeVO.getId())) {
			return Optional.of(employeeRepo.save(employeeVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepo.deleteById(id);
	}

	
	// Leave Type
	
	@Override
	public List<LeaveTypeVO> getAllgetAllLeaveType() {
		
		return leaveTypeRepo.findAll();
	}

	@Override
	public Optional<LeaveTypeVO> getLeaveTypeById(int id) {
		
		return leaveTypeRepo.findById(id);
	}

	@Override
	public LeaveTypeVO createLeaveType(LeaveTypeVO leaveTypeVO) {
		
		return leaveTypeRepo.save(leaveTypeVO);
	}

	@Override
	public Optional<LeaveTypeVO> updateLeaveType(LeaveTypeVO leaveTypeVO) {
		if (leaveTypeRepo.existsById(leaveTypeVO.getId())) {
			return Optional.of(leaveTypeRepo.save(leaveTypeVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteLeaveType(int id) {
		leaveTypeRepo.deleteById(id);
		
	}



}
