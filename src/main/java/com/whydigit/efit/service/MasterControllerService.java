package com.whydigit.efit.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.whydigit.efit.dto.EmployeeDTO;
import com.whydigit.efit.dto.LeaveDetailsDTO;
import com.whydigit.efit.dto.RequestLeaveDTO;
import com.whydigit.efit.entity.CheckinVO;
import com.whydigit.efit.entity.EmployeeVO;
import com.whydigit.efit.entity.LeaveDetailsVO;
import com.whydigit.efit.entity.RequestLeaveVO;
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
	
	//RequestLeave

	Map<String, Object> createUpdateRequestLeave(RequestLeaveDTO requestLeaveDTO) throws ApplicationException;

	List<RequestLeaveVO> getAllNewLeaveRequest(Long orgId,Long empId);

    Optional<RequestLeaveVO> getNewLeaveRequestById(Long id);

	ResponseEntity<List<CheckinVO>> excelUploadForCheckIn(MultipartFile[] files, String createdBy, Long orgId) throws Exception;

	int getTotalRows();

	int getSuccessfulUploads();

	int getSuccessfulUploads1();

	int getTotalRows1();

	List<Map<String, Object>> getAttendanceDetailsOfEmpForMonth(Long orgId, LocalDate fromDate, LocalDate toDate);
	
}
