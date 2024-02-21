package com.whydigit.efit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.LeaveApprovalDTO;
import com.whydigit.efit.dto.UserName;
import com.whydigit.efit.entity.CheckinStatusVO;
import com.whydigit.efit.entity.CheckinVO;
import com.whydigit.efit.entity.EmployeeCheckInTimeVO;
import com.whydigit.efit.entity.EmployeeCheckinDailyStatusVO;
import com.whydigit.efit.entity.EmployeeDetailsVO;
import com.whydigit.efit.entity.HolidayVO;
import com.whydigit.efit.entity.LeaveBalanceVO;
import com.whydigit.efit.entity.LeaveCreditVO;
import com.whydigit.efit.entity.LeaveRequestVO;
import com.whydigit.efit.entity.LeaveTypeVO;
import com.whydigit.efit.entity.PermissionRequestVO;
import com.whydigit.efit.exception.ApplicationException;



@Service
public interface BasicMasterService {
	
	
	List<EmployeeDetailsVO> getAllgetAllEmployees();

	Optional<EmployeeDetailsVO> getEmployeeById(long id);

	EmployeeDetailsVO createEmployee(EmployeeDetailsVO employeeVO) throws ApplicationException;

	Optional<EmployeeDetailsVO> updateEmployee(EmployeeDetailsVO employeeVO);

	void deleteEmployee(long id);
	
	
	
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
	
	Optional<LeaveRequestVO> updateLeaveRequestApproval(LeaveApprovalDTO leaveApprovalDTO,int id);
	

	//NEW PERMISSION REQUEST
	
	List<PermissionRequestVO> getAllPermissionRequest();

	Optional<PermissionRequestVO> getPermissionRequestById(int id);
	
	List<PermissionRequestVO> getPermissionRequestByEmpcode(String empcode);
	
	PermissionRequestVO createPermissionRequest(PermissionRequestVO permissionRequestVO);

	Optional<PermissionRequestVO> updatePermissionRequest(PermissionRequestVO permissionRequestVO);

	void deletePermissionRequest(int id);

	Optional<PermissionRequestVO> updatePermissionRequestApproval(LeaveApprovalDTO leaveApprovalDTO,int id);
	
	
	CheckinVO checkIn(UserName user1);

	CheckinVO checkOut(UserName user1);

	Optional<CheckinStatusVO> getStatusByEmpcode(String empcode);
	
	
	
	List<EmployeeCheckInTimeVO> getAttendanceByEmpcode(String empcode);

	//EMPLOYEE CHECKIN  DAILY STATUS
	
	List<EmployeeCheckinDailyStatusVO> getAllEmployeesCheckinStatusDaily();

	//Create Leave Credit to EMPLOYEES
	LeaveCreditVO createLeaveCredit(LeaveCreditVO leaveCreditVO);

	// Get All Employee Leave Balance
	
	List<LeaveBalanceVO> getAllLeaveBalance();
	
	//get Leave Balance by Empcode
	List<LeaveBalanceVO> getLeaveBalanceByEmpcode(String empcode);
	
	
	

	





}
