package com.whydigit.efit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.UserName;
import com.whydigit.efit.dto.logincreationDTO;
import com.whydigit.efit.entity.CheckinVO;
import com.whydigit.efit.entity.EmployeeDetailsVO;
import com.whydigit.efit.entity.HolidayVO;
import com.whydigit.efit.entity.LeaveRequestVO;
import com.whydigit.efit.entity.LeaveTypeVO;
import com.whydigit.efit.entity.PermissionRequestVO;



@Service
public interface BasicMasterService {
	
	
	List<EmployeeDetailsVO> getAllgetAllEmployees();

	Optional<EmployeeDetailsVO> getEmployeeById(int id);

	EmployeeDetailsVO createEmployee(EmployeeDetailsVO employeeVO);

	Optional<EmployeeDetailsVO> updateEmployee(EmployeeDetailsVO employeeVO);

	void deleteEmployee(int id);
	
	
	
	List<LeaveTypeVO> getAllgetAllLeaveType();
	
	Optional<LeaveTypeVO> getLeavetypeById(int id);

	LeaveTypeVO createLeaveType(LeaveTypeVO leaveTypeVO);

	Optional<LeaveTypeVO> updateLeaveType(LeaveTypeVO leaveTypeVO);

	void deleteLeaveType(int id);
	

	//Holiday Service
	
	List<HolidayVO> getAllHolidayType();
	
	Optional<HolidayVO> getholidayById(int id);

	HolidayVO createholidayType(HolidayVO holidayVO);

	Optional<HolidayVO> updateHolidayType(HolidayVO holidayVO);

	void deleteHolidayType(int id);

	
	//LEAVE REQUEST
	List<LeaveRequestVO> getAllLeaveRequest();
	
	Optional<LeaveRequestVO> getLeaveRequestById(int id);
	
	List<LeaveRequestVO> getLeaveRequestByEmpcode(String empcode);

	LeaveRequestVO createLeaveRequest(LeaveRequestVO laveRequestVO);

	Optional<LeaveRequestVO> updateLeaveRequest(LeaveRequestVO leaveRequestVO);

	void deleteLeaveRequest(int id);
	
	Optional<LeaveRequestVO> updateLeaveRequestApproval(LeaveRequestVO leaveRequestVO);
	

	//NEW PERMISSION REQUEST
	
	List<PermissionRequestVO> getAllPermissionRequest();

	Optional<PermissionRequestVO> getPermissionRequestById(int id);
	
	List<PermissionRequestVO> getPermissionRequestByEmpcode(String empcode);
	
	PermissionRequestVO createPermissionRequest(PermissionRequestVO permissionRequestVO);

	Optional<PermissionRequestVO> updatePermissionRequest(PermissionRequestVO permissionRequestVO);

	void deletePermissionRequest(int id);

	Optional<PermissionRequestVO> updatePermissionRequestApproval(PermissionRequestVO permissionRequestVO);
	
	
	CheckinVO checkIn(UserName user1);

	CheckinVO checkOut(UserName user1);

	
	
	
	


	
	
	
	

	





}
