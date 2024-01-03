package com.whydigit.efit.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whydigit.efit.common.CommonConstant;
import com.whydigit.efit.common.UserConstants;
import com.whydigit.efit.dto.ResponseDTO;
import com.whydigit.efit.entity.EmployeeDetailsVO;
import com.whydigit.efit.entity.LeaveTypeVO;
import com.whydigit.efit.service.BasicMasterService;


@RestController
@CrossOrigin
@RequestMapping("/api/basicMaster")
public class BasicMasterController extends BaseController {

	public static final Logger LOGGER = LoggerFactory.getLogger(BasicMasterController.class);

	@Autowired
	private BasicMasterService basicMasterService;

	//	employee

	@GetMapping("/employee")
	public ResponseEntity<ResponseDTO> getAllEmployees() {
		String methodName = "getAllEmployees()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<EmployeeDetailsVO> employeeVO = new ArrayList<>();
		try {
			employeeVO = basicMasterService.getAllgetAllEmployees();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "employee information get successfully");
			responseObjectsMap.put("employeeVO", employeeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "employee information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping("/employee")
	public ResponseEntity<ResponseDTO> createEmployee(@RequestBody EmployeeDetailsVO employeeVO) {
		String methodName = "createEmployee()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			EmployeeDetailsVO createdEmployeeVO = basicMasterService.createEmployee(employeeVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "employee created successfully");
			responseObjectsMap.put("employeeVO", createdEmployeeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "employee creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	
	@PutMapping("/employee")
	public ResponseEntity<ResponseDTO> updateEmployee(@RequestBody EmployeeDetailsVO employeeVO) {
		String methodName = "updateEmployee()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			EmployeeDetailsVO updatedEmployeeVO = basicMasterService.updateEmployee(employeeVO).orElse(null);
			if (updatedEmployeeVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Employee updated successfully");
				responseObjectsMap.put("employeeVO", updatedEmployeeVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Employee not found for ID: " + employeeVO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Employee update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Employee update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}


	@DeleteMapping("/employee/{id}")
	public ResponseEntity<ResponseDTO> deleteEmployee(@PathVariable int id) {
		String methodName = "deleteEmployee()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			basicMasterService.deleteEmployee(id);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "employee deleted successfully");
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "employee deletion failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	
//	Leave Type

	@GetMapping("/leavetype")
	public ResponseEntity<ResponseDTO> getAllLeaveType() {
		String methodName = "getAllLeaveType()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<LeaveTypeVO> leaveTypeVO = new ArrayList<>();
		try {
			leaveTypeVO = basicMasterService.getAllgetAllLeaveType();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Leave Type get successfully");
			responseObjectsMap.put("leaveTypeVO", leaveTypeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Leave Type receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping("/leavetype")
	public ResponseEntity<ResponseDTO> createLeaveType(@RequestBody LeaveTypeVO leaveTypeVO) {
		String methodName = "createLeaveType()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			LeaveTypeVO createLeaveTypeVO = basicMasterService.createLeaveType(leaveTypeVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Leave Type created successfully");
			responseObjectsMap.put("leaveTypeVO", createLeaveTypeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Leave Type creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	
	@PutMapping("/leavetype")
	public ResponseEntity<ResponseDTO> updateLeaveType(@RequestBody LeaveTypeVO leaveTypeVO) {
		String methodName = "updateLeaveType()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			LeaveTypeVO updatedLeaveTypeVO = basicMasterService.updateLeaveType(leaveTypeVO).orElse(null);
			if (updatedLeaveTypeVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Leave Type updated successfully");
				responseObjectsMap.put("leaveTypeVO", updatedLeaveTypeVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Leave Type not found for ID: " + leaveTypeVO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Leave Type update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Leave Type update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}


	@DeleteMapping("/leavetype/{id}")
	public ResponseEntity<ResponseDTO> deleteLeaveType(@PathVariable int id) {
		String methodName = "deleteLeaveType()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			basicMasterService.deleteLeaveType(id);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Leave Type deleted successfully");
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Leave Type deletion failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}



}
