package com.whydigit.efit.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.whydigit.efit.common.CommonConstant;
import com.whydigit.efit.common.UserConstants;
import com.whydigit.efit.dto.EmployeeDTO;
import com.whydigit.efit.dto.LeaveDetailsDTO;
import com.whydigit.efit.dto.RequestLeaveDTO;
import com.whydigit.efit.dto.ResponseDTO;
import com.whydigit.efit.entity.EmployeeVO;
import com.whydigit.efit.entity.LeaveDetailsVO;
import com.whydigit.efit.entity.LeaveRequestVO;
import com.whydigit.efit.entity.RequestLeaveVO;
import com.whydigit.efit.service.MasterControllerService;

@RestController
@CrossOrigin
@RequestMapping("/api/masterController")
public class MasterController extends BaseController{

	@Autowired
	MasterControllerService masterControllerService;
	
	//LeaveDeatils
	
	@PutMapping("/createUpdateLeaveDeatils")
	public ResponseEntity<ResponseDTO> createUpdateLeaveDeatils(@RequestBody LeaveDetailsDTO leaveDetailsDTO) {
		String methodName = "createUpdateLeaveDeatils()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> leaveDetailsVO = masterControllerService.createUpdateLeaveDeatils(leaveDetailsDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, leaveDetailsVO.get("message"));
			responseObjectsMap.put("leaveDetailsVO", leaveDetailsVO.get("leaveDetailsVO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@GetMapping("/getAllLeaveDetails")
	public ResponseEntity<ResponseDTO> getAllLeaveDetails(@RequestParam(required = true) Long orgId) {
		String methodName = "getAllLeaveDetails()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<LeaveDetailsVO> leaveDetailsVO = new ArrayList<>();
		try {
			leaveDetailsVO = masterControllerService.getAllLeaveDetails(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Leave Details information get successfully");
			responseObjectsMap.put("leaveDetailsVO", leaveDetailsVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Leave Details  information get Failed ",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@GetMapping("/getAllLeaveDetailsById")
	public ResponseEntity<ResponseDTO> getAllLeaveDetailsById(@RequestParam(required = true) Long id) {
		String methodName = "getAllLeaveDetailsById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		Optional<LeaveDetailsVO> leaveDetailsVO = null;
		try {
			leaveDetailsVO = masterControllerService.getAllLeaveDetailsById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Leave Details information get successfully Id");
			responseObjectsMap.put("leaveDetailsVO", leaveDetailsVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Leave Details  information get Failed ",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@GetMapping("/getLeaveCode")
	public ResponseEntity<ResponseDTO> getLeaveCode(@RequestParam(required = true) Long orgId) {
		String methodName = "getLeaveCode()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<Map<String, Object>> leaveCode=new ArrayList<Map<String,Object>>();
		try {
			leaveCode = masterControllerService.getLeaveCode(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Leave Code information get successfully");
			responseObjectsMap.put("leaveCode", leaveCode);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Leave Code  information get Failed ",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	//EMPLOYEE CREATION NEW
	
	@PutMapping("/createUpdateEmployee")
	public ResponseEntity<ResponseDTO> createUpdateEmployee(@RequestBody EmployeeDTO employeeDTO) {
		String methodName = "createUpdateEmployee()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> employeeVO = masterControllerService.createUpdateEmployee(employeeDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, employeeVO.get("message"));
			responseObjectsMap.put("employeeVO", employeeVO.get("employeeVO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@GetMapping("/getEmployeeById")
	public ResponseEntity<ResponseDTO> getEmployeeById(@RequestParam(required = true) Long id) {
		String methodName = "getAllLeaveDetailsById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		Optional<EmployeeVO> employeeVO = null;
		try {
			employeeVO = masterControllerService.getEmployeeById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Employee information get successfully Id");
			responseObjectsMap.put("employeeVO", employeeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Employee  information get Failed ",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@GetMapping("/getAllEmployee")
	public ResponseEntity<ResponseDTO> getAllEmployee(@RequestParam(required = true) Long orgId) {
		String methodName = "getAllEmployee()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<EmployeeVO> employeeVO = new ArrayList<EmployeeVO>();
		try {
			employeeVO = masterControllerService.getAllEmployee(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Employee information get successfully OrgId");
			responseObjectsMap.put("employeeVO", employeeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Employee  information get Failed ",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@GetMapping("/getEmployeeLeaveCount")
	public ResponseEntity<ResponseDTO> getEmployeeLeaveCount(@RequestParam(required = true) Long orgId,@RequestParam(required = true) Long empId) {
		String methodName = "getEmployeeLeaveCount()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<Map<String, Object>> employeeLeaveCount=new ArrayList<Map<String,Object>>();
		try {
			employeeLeaveCount = masterControllerService.getEmployeeLeaveCount(orgId,empId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "createUpdateRequestLeave information get successfully");
			responseObjectsMap.put("employeeLeaveCount", employeeLeaveCount);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Employee Leave Count  information get Failed ",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	//LEAVE REQUEST
	
	@PutMapping("/createUpdateRequestLeave")
	public ResponseEntity<ResponseDTO> createUpdateRequestLeave(@RequestBody RequestLeaveDTO requestLeaveDTO) {
		String methodName = "createUpdateRequestLeave()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> requestLeaveVO = masterControllerService.createUpdateRequestLeave(requestLeaveDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, requestLeaveVO.get("message"));
			responseObjectsMap.put("requestLeaveVO", requestLeaveVO.get("requestLeaveVO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@GetMapping("/getAllNewLeaveRequest")
	public ResponseEntity<ResponseDTO> getAllNewLeaveRequest(@RequestParam(required = true) Long orgId,@RequestParam(required =false) Long empId) {
		String methodName = "getAllNewLeaveRequest()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<RequestLeaveVO> requestLeaveVO = new ArrayList<>();
		try {
			requestLeaveVO = masterControllerService.getAllNewLeaveRequest(orgId,empId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Leave Request get successfully OrgId");
			responseObjectsMap.put("requestLeaveVO", requestLeaveVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Leave Request  information get Failed ",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@GetMapping("/getNewLeaveRequestById")
	public ResponseEntity<ResponseDTO> getNewLeaveRequestById(@RequestParam(required = true) Long id) {
		String methodName = "getNewLeaveRequestById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		Optional<RequestLeaveVO> requestLeaveVO = null;
		try {
			requestLeaveVO = masterControllerService.getNewLeaveRequestById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Leave Request get successfully Id");
			responseObjectsMap.put("requestLeaveVO", requestLeaveVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Leave Request  information get Failed ",
					errorMsg); 
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@PostMapping("/excelUploadForCheckIn")
	public ResponseEntity<ResponseDTO> excelUploadForCheckIn(@RequestParam MultipartFile[] files,@RequestParam(required = false) String createdBy,
			@RequestParam(required = false) Long orgId) {
		String methodName = "excelUploadForCheckIn()";
		int totalRows = 0;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		int successfulUploads = 0;
		ResponseDTO responseDTO = null;
		try {
			// Call service method to process Excel upload
			masterControllerService.excelUploadForCheckIn(files,createdBy,orgId);

			// Retrieve the counts after processing
			totalRows = masterControllerService.getTotalRows(); // Get total rows processed
			successfulUploads = masterControllerService.getSuccessfulUploads(); // Get successful uploads count
			responseObjectsMap.put("statusFlag", "Ok");
	        responseObjectsMap.put("status", true);
	        responseObjectsMap.put("totalRows", totalRows);
	        responseObjectsMap.put("successfulUploads", successfulUploads);
	        responseObjectsMap.put("message", "Excel Upload For ElMfr successful"); // Directly include the message here
	        responseDTO = createServiceResponse(responseObjectsMap);

	    } catch (Exception e) {
	        String errorMsg = e.getMessage();
	        LOGGER.error(CommonConstant.EXCEPTION, methodName, e);
	        responseObjectsMap.put("statusFlag", "Error");
	        responseObjectsMap.put("status", false);
	        responseObjectsMap.put("errorMessage", errorMsg);

	        responseDTO = createServiceResponseError(responseObjectsMap, "Excel Upload For CheckIn Failed", errorMsg);
	    }
	    LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
	    return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getAttendanceDetailsOfEmpForMonth")
	public ResponseEntity<ResponseDTO> getAttendanceDetailsOfEmpForMonth(@RequestParam(required = true) Long orgId,@RequestParam(required = true) LocalDate fromDate,
			@RequestParam(required = true) LocalDate toDate	) {
		String methodName = "getAttendanceDetailsOfEmpForMonth()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<Map<String, Object>> attendanceDetails=new ArrayList<Map<String,Object>>();
		try {
			attendanceDetails = masterControllerService.getAttendanceDetailsOfEmpForMonth(orgId,fromDate,toDate);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Emp Attendance information get successfully");
			responseObjectsMap.put("attendanceDetails", attendanceDetails);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Emp Attendance  information get Failed ",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
}
