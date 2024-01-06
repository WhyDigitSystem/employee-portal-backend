package com.whydigit.efit.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.whydigit.efit.dto.UserName;
import com.whydigit.efit.entity.CheckinVO;
import com.whydigit.efit.entity.EmployeeDetailsVO;
import com.whydigit.efit.entity.HolidayVO;
import com.whydigit.efit.entity.LeaveRequestVO;
import com.whydigit.efit.entity.LeaveTypeVO;
import com.whydigit.efit.entity.PermissionRequestVO;
import com.whydigit.efit.repo.CheckinRepo;
import com.whydigit.efit.repo.EmployeeDetailsRepo;
import com.whydigit.efit.repo.HolidayRepo;
import com.whydigit.efit.repo.LeaveRequestRepo;
import com.whydigit.efit.repo.LeaveTypeRepo;
import com.whydigit.efit.repo.PermissionRequestRepo;

@Service
public class BasicMasterServiceImpl implements BasicMasterService {

	@Autowired
	private EmployeeDetailsRepo employeeRepo;

	@Autowired
	private LeaveTypeRepo leaveTypeRepo;

	@Autowired
	private HolidayRepo holidayRepo;

	@Autowired
	private LeaveRequestRepo newLeaveRequestRepo;

	@Autowired
	private PermissionRequestRepo newPermissionRequestRepo;
	
	@Autowired
	CheckinRepo checkinRepo;

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
	public List<LeaveRequestVO> getAllLeaveRequest() {
		// TODO Auto-generated method stub
		return newLeaveRequestRepo.findAll();
	}

	@Override
	public Optional<LeaveRequestVO> getLeaveRequestById(int id) {
		// TODO Auto-generated method stub
		return newLeaveRequestRepo.findById(id);
	}

	@Override
	public LeaveRequestVO createLeaveRequest(LeaveRequestVO newLeaveRequestVO) {
		// TODO Auto-generated method stub
		return newLeaveRequestRepo.save(newLeaveRequestVO);
	}

	@Override
	public Optional<LeaveRequestVO> updateLeaveRequest(LeaveRequestVO newLeaveRequestVO) {
		if (newLeaveRequestRepo.existsById(newLeaveRequestVO.getId())) {
			return Optional.of(newLeaveRequestRepo.save(newLeaveRequestVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteLeaveRequest(int id) {
		// TODO Auto-generated method stub
		newLeaveRequestRepo.deleteById(id);
	}

	// permission Request

	@Override
	public List<PermissionRequestVO> getAllPermissionRequest() {
		// TODO Auto-generated method stub
		return newPermissionRequestRepo.findAll();
	}

	@Override
	public Optional<PermissionRequestVO> getPermissionRequestById(int id) {
		// TODO Auto-generated method stub
		return newPermissionRequestRepo.findById(id);
	}

	@Override
	public PermissionRequestVO createPermissionRequest(PermissionRequestVO newPermissionRequestVO) {
		// TODO Auto-generated method stub
		return newPermissionRequestRepo.save(newPermissionRequestVO);
	}

	@Override
	public Optional<PermissionRequestVO> updatePermissionRequest(PermissionRequestVO newPermissionRequestVO) {
		if (newPermissionRequestRepo.existsById(newPermissionRequestVO.getId())) {
			return Optional.of(newPermissionRequestRepo.save(newPermissionRequestVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deletePermissionRequest(int id) {

		newPermissionRequestRepo.deleteById(id);
	}
	
	public CheckinVO checkIn(UserName user) {
    	Date current= new Date();
    	CheckinVO checkinVO = new CheckinVO();
    	checkinVO.setCheckin(current);
    	checkinVO.setStatus("In");
    	checkinVO.setUserid(user.getUserid());
    	checkinVO.setCheckInTime(current);
        return checkinRepo.save(checkinVO);
    }

	public CheckinVO checkOut(int id) {
    	
    	Date current= new Date();
    	
    	CheckinVO checkinVO = checkinRepo.findById(id).get();

    	checkinVO.setCheckOutTime(current);
    	checkinVO.setStatus("Out");
        return checkinRepo.save(checkinVO);
    }

}
