package com.whydigit.efit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whydigit.efit.entity.EmployeeDetailsVO;
import com.whydigit.efit.entity.HolidayVO;
import com.whydigit.efit.entity.LeaveTypeVO;
import com.whydigit.efit.entity.NewLeaveRequestVO;
import com.whydigit.efit.entity.NewPermissionRequestVO;


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
	List<NewLeaveRequestVO> getAllNewLeaveRequest();
	
	Optional<NewLeaveRequestVO> getLeaverequestById(int id);

	NewLeaveRequestVO createNewLeaveRequest(NewLeaveRequestVO newLeaveRequestVO);

	Optional<NewLeaveRequestVO> updateNewLeaveRequest(NewLeaveRequestVO newLeaveRequestVO);

	void deleteNewLeaveRequest(int id);
	

	//NEW PERMISSION REQUEST
	
	List<NewPermissionRequestVO> getAllNewPermissionRequest();

	Optional<NewPermissionRequestVO> getPermissionRequestById(int id);
	
	NewPermissionRequestVO createNewPermissionRequest(NewPermissionRequestVO newPermissionRequestVO);

	Optional<NewPermissionRequestVO> updateNewPermissionRequest(NewPermissionRequestVO newPermissionRequestVO);

	void deleteNewPermissionRequest(int id);

	
	
	
	


	
	
	
	

	





}
