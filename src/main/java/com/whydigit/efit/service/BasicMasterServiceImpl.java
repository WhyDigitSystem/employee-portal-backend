package com.whydigit.efit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whydigit.efit.entity.EmployeeDetailsVO;
import com.whydigit.efit.entity.HolidayVO;
import com.whydigit.efit.entity.LeaveTypeVO;
import com.whydigit.efit.entity.NewLeaveRequestVO;
import com.whydigit.efit.entity.NewPermissionRequestVO;
import com.whydigit.efit.repo.EmployeeDetailsRepo;
import com.whydigit.efit.repo.HolidayRepo;
import com.whydigit.efit.repo.LeaveTypeRepo;
import com.whydigit.efit.repo.NewLeaveRequestRepo;
import com.whydigit.efit.repo.NewPermissionRequestRepo;

@Service
public class BasicMasterServiceImpl implements BasicMasterService {

	@Autowired
	private EmployeeDetailsRepo employeeRepo;

	@Autowired
	private LeaveTypeRepo leaveTypeRepo;

	@Autowired
	private HolidayRepo holidayRepo;

	@Autowired
	private NewLeaveRequestRepo newLeaveRequestRepo;

	@Autowired
	private NewPermissionRequestRepo newPermissionRequestRepo;

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
	public Optional<LeaveTypeVO> getLeavetypeById(int id) {
		// TODO Auto-generated method stub
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

	// HOLIDAY Type

	@Override
	public List<HolidayVO> getAllHolidayType() {

		return holidayRepo.findAll();
	}

	@Override
	public Optional<HolidayVO> getholidayById(int id) {
		// TODO Auto-generated method stub
		return holidayRepo.findById(id);
	}

	@Override
	public HolidayVO createholidayType(HolidayVO holidayVO) {

		return holidayRepo.save(holidayVO);
	}

	@Override
	public Optional<HolidayVO> updateHolidayType(HolidayVO holidayVO) {
		if (holidayRepo.existsById(holidayVO.getId())) {
			return Optional.of(holidayRepo.save(holidayVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteHolidayType(int id) {
		holidayRepo.deleteById(id);

	}

	// NewLeave

	@Override
	public List<NewLeaveRequestVO> getAllNewLeaveRequest() {
		// TODO Auto-generated method stub
		return newLeaveRequestRepo.findAll();
	}

	@Override
	public Optional<NewLeaveRequestVO> getLeaverequestById(int id) {
		// TODO Auto-generated method stub
		return newLeaveRequestRepo.findById(id);
	}

	@Override
	public NewLeaveRequestVO createNewLeaveRequest(NewLeaveRequestVO newLeaveRequestVO) {
		// TODO Auto-generated method stub
		return newLeaveRequestRepo.save(newLeaveRequestVO);
	}

	@Override
	public Optional<NewLeaveRequestVO> updateNewLeaveRequest(NewLeaveRequestVO newLeaveRequestVO) {
		if (newLeaveRequestRepo.existsById(newLeaveRequestVO.getId())) {
			return Optional.of(newLeaveRequestRepo.save(newLeaveRequestVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteNewLeaveRequest(int id) {
		// TODO Auto-generated method stub
		newLeaveRequestRepo.deleteById(id);
	}

	// permission Request

	@Override
	public List<NewPermissionRequestVO> getAllNewPermissionRequest() {
		// TODO Auto-generated method stub
		return newPermissionRequestRepo.findAll();
	}

	@Override
	public Optional<NewPermissionRequestVO> getPermissionRequestById(int id) {
		// TODO Auto-generated method stub
		return newPermissionRequestRepo.findById(id);
	}

	@Override
	public NewPermissionRequestVO createNewPermissionRequest(NewPermissionRequestVO newPermissionRequestVO) {
		// TODO Auto-generated method stub
		return newPermissionRequestRepo.save(newPermissionRequestVO);
	}

	@Override
	public Optional<NewPermissionRequestVO> updateNewPermissionRequest(NewPermissionRequestVO newPermissionRequestVO) {
		if (newPermissionRequestRepo.existsById(newPermissionRequestVO.getId())) {
			return Optional.of(newPermissionRequestRepo.save(newPermissionRequestVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteNewPermissionRequest(int id) {

		newPermissionRequestRepo.deleteById(id);
	}

}
