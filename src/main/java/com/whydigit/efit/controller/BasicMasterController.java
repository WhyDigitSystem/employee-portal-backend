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
import com.whydigit.efit.entity.HolidayVO;
import com.whydigit.efit.entity.LeaveTypeVO;
import com.whydigit.efit.entity.NewLeaveRequestVO;
import com.whydigit.efit.entity.NewPermissionRequestVO;
import com.whydigit.efit.service.BasicMasterService;

@RestController
@CrossOrigin
@RequestMapping("/api/basicMaster")
public class BasicMasterController extends BaseController {

	public static final Logger LOGGER = LoggerFactory.getLogger(BasicMasterController.class);

	@Autowired
	private BasicMasterService basicMasterService;

	// employee

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

	@GetMapping("/employee/{id}")
	public ResponseEntity<ResponseDTO> getEmployeeById(@PathVariable int id) {
		String methodName = "getEmployeeById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		EmployeeDetailsVO employeeDetailsVO = null;
		try {
			employeeDetailsVO = basicMasterService.getEmployeeById(id).orElse(null);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Employee found by ID");
			responseObjectsMap.put("Employee", employeeDetailsVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "Employee not found for ID: " + id;
			responseDTO = createServiceResponseError(responseObjectsMap, "Employee not found", errorMsg);
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
			responseDTO = createServiceResponseError(responseObjectsMap, "Leave Type receive failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@GetMapping("/leavetype/{id}")
	public ResponseEntity<ResponseDTO> getLeavetypeById(@PathVariable int id) {
		String methodName = "getLeavetypeById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		LeaveTypeVO leaveTypeVO = null;
		try {
			leaveTypeVO = basicMasterService.getLeavetypeById(id).orElse(null);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Employee found by ID");
			responseObjectsMap.put("LeaveTypeVO", leaveTypeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "LeaveType not found for ID: " + id;
			responseDTO = createServiceResponseError(responseObjectsMap, "LeaveType not found", errorMsg);
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

//	Holidays Screen

	@GetMapping("/holiday")
	public ResponseEntity<ResponseDTO> getAllHolidayType() {
		String methodName = "getAllLeaveType()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<HolidayVO> holidayVO = new ArrayList<>();
		try {
			holidayVO = basicMasterService.getAllHolidayType();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Holiday get successfully");
			responseObjectsMap.put("holidayVO", holidayVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Holiday receive failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	@GetMapping("/holiday/{id}")
	public ResponseEntity<ResponseDTO> getholidayById(@PathVariable int id) {
		String methodName = "getholidayById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		HolidayVO holidayVO = null;
		try {
			holidayVO = basicMasterService.getholidayById(id).orElse(null);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Holiday found by ID");
			responseObjectsMap.put("HolidayVO", holidayVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "Holiday not found for ID: " + id;
			responseDTO = createServiceResponseError(responseObjectsMap, "Holiday not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	@PostMapping("/holiday")
	public ResponseEntity<ResponseDTO> createHolidayType(@RequestBody HolidayVO holidayVO) {
		String methodName = "createLeaveType()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			HolidayVO createholidayTypeVO = basicMasterService.createholidayType(holidayVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Holiday  created successfully");
			responseObjectsMap.put("holidayVO", createholidayTypeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Holiday creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/holiday")
	public ResponseEntity<ResponseDTO> updateHolidayType(@RequestBody HolidayVO holidayVO) {
		String methodName = "updateHolidayeType()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			HolidayVO updateHolidayVO = basicMasterService.updateHolidayType(holidayVO).orElse(null);
			if (updateHolidayVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Holiday updated successfully");
				responseObjectsMap.put("holidayVO", updateHolidayVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Holiday Type not found for ID: " + holidayVO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Holiday update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Holiday update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@DeleteMapping("/holiday/{id}")
	public ResponseEntity<ResponseDTO> deleteHolidayType(@PathVariable int id) {
		String methodName = "deleteHolidayType()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			basicMasterService.deleteHolidayType(id);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Holiday deleted successfully");
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Holiday deletion failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// FOR LEAVE REQUEST

	@GetMapping("/leaverequest")
	public ResponseEntity<ResponseDTO> getAllNewLeaveRequest() {
		String methodName = "getAllNewLeaveRequest()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<NewLeaveRequestVO> newLeaveRequestVO = new ArrayList<>();
		try {
			newLeaveRequestVO = basicMasterService.getAllNewLeaveRequest();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "LeaveRequest information get successfully");
			responseObjectsMap.put("newLeaveRequestVO", newLeaveRequestVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "LeaveRequest information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/leaverequest/{id}")
	public ResponseEntity<ResponseDTO> getLeaverequestById(@PathVariable int id) {
		String methodName = "getLeaverequestById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		NewLeaveRequestVO newLeaveRequestVO = null;
		try {
			newLeaveRequestVO = basicMasterService.getLeaverequestById(id).orElse(null);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Leaverequest found by ID");
			responseObjectsMap.put("NewLeaveRequestVO", newLeaveRequestVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "Leaverequest not found for ID: " + id;
			responseDTO = createServiceResponseError(responseObjectsMap, "Leaverequest not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	@PostMapping("/leaverequest")
	public ResponseEntity<ResponseDTO> createNewLeaveRequest(@RequestBody NewLeaveRequestVO newLeaveRequestVO) {
		String methodName = "createNewLeaveRequest()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			NewLeaveRequestVO createNewLeaveRequestVO = basicMasterService.createNewLeaveRequest(newLeaveRequestVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "LeaveRequest created successfully");
			responseObjectsMap.put("newLeaveRequestVO", createNewLeaveRequestVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "LeaveRequest creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/leaverequest")
	public ResponseEntity<ResponseDTO> updateNewLeaveRequest(@RequestBody NewLeaveRequestVO newLeaveRequestVO) {
		String methodName = "updateNewLeaveRequest()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			NewLeaveRequestVO updateNewLeaveRequestVO = basicMasterService.updateNewLeaveRequest(newLeaveRequestVO)
					.orElse(null);
			if (updateNewLeaveRequestVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "LeaveRequest updated successfully");
				responseObjectsMap.put("NewLeaveRequestVO", updateNewLeaveRequestVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "NewLeaveRequest Type not found for ID: " + newLeaveRequestVO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "LeaveRequest update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "LeaveRequest update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@DeleteMapping("/leaverequest/{id}")
	public ResponseEntity<ResponseDTO> deleteNewLeaveRequest(@PathVariable int id) { // NewLeaveRequestVO
																						// newLeaveRequestVO
		String methodName = "deleteNewLeaveRequest()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			basicMasterService.deleteNewLeaveRequest(id);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "LeaveRequest deleted successfully");
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "LeaveRequest deletion failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
//	NEW PERMISSION REQUEST

	@GetMapping("/permissionRequest")
	public ResponseEntity<ResponseDTO> getAllNewPermissionRequest() {
		String methodName = "getAllNewPermissionRequest()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<NewPermissionRequestVO> newPermissionRequestVO = new ArrayList<>();
		try {
			newPermissionRequestVO = basicMasterService.getAllNewPermissionRequest();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Permission Request information get successfully");
			responseObjectsMap.put("newPermissionRequestVO", newPermissionRequestVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Permission Request information receive failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/permissionRequest/{id}")
	public ResponseEntity<ResponseDTO> getPermissionRequestById(@PathVariable int id) {
		String methodName = "getPermissionRequestById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		NewPermissionRequestVO newPermissionRequestVO = null;
		try {
			newPermissionRequestVO = basicMasterService.getPermissionRequestById(id).orElse(null);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "PermissionRequest found by ID");
			responseObjectsMap.put("NewPermissionRequestVO", newPermissionRequestVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "Employee not found for ID: " + id;
			responseDTO = createServiceResponseError(responseObjectsMap, "PermissionRequest not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	@PostMapping("/permissionRequest")
	public ResponseEntity<ResponseDTO> createNewPermissionRequest(
			@RequestBody NewPermissionRequestVO newPermissionRequestVO) {
		String methodName = "createEmployeeNewPermissionRequest()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			NewPermissionRequestVO createdNewPermissionRequestVO = basicMasterService
					.createNewPermissionRequest(newPermissionRequestVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Permission Request created successfully");
			responseObjectsMap.put("newPermissionRequestVO", createdNewPermissionRequestVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Permission Request creation failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/permissionRequest")
	public ResponseEntity<ResponseDTO> updateNewPermissionRequest(
			@RequestBody NewPermissionRequestVO newPermissionRequestVO) {
		String methodName = "updateNewPermissionRequest()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			NewPermissionRequestVO updatedNewPermissionRequestVO = basicMasterService
					.updateNewPermissionRequest(newPermissionRequestVO).orElse(null);
			if (updatedNewPermissionRequestVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Permission Request updated successfully");
				responseObjectsMap.put("updateNewPermissionRequest", updatedNewPermissionRequestVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "New Permission Request not found for ID: " + newPermissionRequestVO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Permission Request update failed",
						errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Permission Request update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@DeleteMapping("/permissionRequest/{id}")
	public ResponseEntity<ResponseDTO> deleteNewPermissionRequest(@PathVariable int id) {
		String methodName = "deleteNewPermissionRequest()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName); // NewPermissionRequestVO
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			basicMasterService.deleteNewPermissionRequest(id);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Permission deleted successfully");
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Permission Request deletion failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

}
