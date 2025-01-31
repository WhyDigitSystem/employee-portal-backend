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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whydigit.efit.common.CommonConstant;
import com.whydigit.efit.common.EmployeePortalConstants;
import com.whydigit.efit.common.UserConstants;
import com.whydigit.efit.dto.ResponseDTO;
import com.whydigit.efit.dto.SalaryStructureDTO;
import com.whydigit.efit.entity.EmployeeVO;
import com.whydigit.efit.entity.SalaryMasterVO;
import com.whydigit.efit.entity.SalaryStructureVO;
import com.whydigit.efit.service.SalaryStructureService;

@RestController
@RequestMapping("/api/SalaryStructure")
public class SalaryStructureController  extends BaseController{
	
	public static final Logger LOGGER = LoggerFactory.getLogger(SalaryStructureController.class);
	
	@Autowired
	SalaryStructureService salaryStructureService;
	
	
	@GetMapping("/empNameDetails")
	public ResponseEntity<ResponseDTO> empNameDetails(@RequestParam(required = true) Long orgId) {
		String methodName = "empNameDetails()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<EmployeeVO> employeeVO = new ArrayList<>();
		try {
			employeeVO = salaryStructureService.getEmployeeNameDetails(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Employee Details information get successfully Id");
			responseObjectsMap.put("employeeVO", employeeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Employee Details  information get Failed ",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@PutMapping("/createSalaryStructure")
	public ResponseEntity<ResponseDTO> createSalaryStructure(@RequestBody SalaryStructureDTO salaryStructureDTO) {
		String methodName = "createSalaryStructure()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> salaryStructureVO = salaryStructureService.createUpdateSalaryStructure(salaryStructureDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, salaryStructureVO.get("message"));
			responseObjectsMap.put("salaryStructureVO", salaryStructureVO.get("salaryStructureVO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@GetMapping("/salaryMasterDetails")
	public ResponseEntity<ResponseDTO> salaryMasterDetails(@RequestParam(required = true) Long orgId) {
		String methodName = "salaryMasterDetails()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<SalaryMasterVO> salaryMasterDetails = new ArrayList<>();
		try {
			salaryMasterDetails = salaryStructureService.getAllSalaryMasterForSalaryStructure(orgId);
			} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Salary Master Details information get successfully Id");
			responseObjectsMap.put("salaryMasterDetails", salaryMasterDetails);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "salary Master Details  information get Failed ",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@GetMapping("/getAllSalaryStructure")
	public ResponseEntity<ResponseDTO> getAllSalaryStructure(@RequestParam Long orgId) {
		String methodName = "getAllSalaryStructure()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<SalaryStructureVO> salaryStructureVO = new ArrayList<>();
		try {
			salaryStructureVO = salaryStructureService.getAllSalaryStructures(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(EmployeePortalConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Salary Structure information get successfully");
			responseObjectsMap.put("salaryStructureVO", salaryStructureVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Salary Structure information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getSalaryStructureById")
	public ResponseEntity<ResponseDTO> getSalaryStructureById(@RequestParam Long id) {
		String methodName = "getSalaryStructureById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		SalaryStructureVO salaryStructureVO = null;
		try {
			salaryStructureVO = salaryStructureService.getSalaryStructureById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(EmployeePortalConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Salary Structure found by ID");
			responseObjectsMap.put("salaryStructureVO", salaryStructureVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "Salary Structure not found for ID: " + id;
			responseDTO = createServiceResponseError(responseObjectsMap, "Salary Structure not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

}
