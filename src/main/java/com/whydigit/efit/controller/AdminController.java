package com.whydigit.efit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whydigit.efit.common.CommonConstant;
import com.whydigit.efit.common.EmployeePortalConstants;
import com.whydigit.efit.dto.CreateOrganizationFormDTO;
import com.whydigit.efit.dto.CreateUserFormDTO;
import com.whydigit.efit.dto.ResponseDTO;
import com.whydigit.efit.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController extends BaseController {
	public static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	AdminService adminService;

	@PostMapping("/createUser")
	public ResponseEntity<ResponseDTO> createUser(@Valid @RequestBody CreateUserFormDTO createUserFormDTO) {
		String methodName = "createUser()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			adminService.createUser(createUserFormDTO);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(EmployeePortalConstants.ERROR_MSG_METHOD_NAME_WITH_USER_NAME, methodName, createUserFormDTO.getEmail(),
					errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, EmployeePortalConstants.USER_REGISTERED_SUCCESS_MESSAGE);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, EmployeePortalConstants.USER_REGISTERED_FAILED_MESSAGE,
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping("/createOrginization")
	public ResponseEntity<ResponseDTO> createOrginization(
			@Valid @RequestBody CreateOrganizationFormDTO createOrganizationFormDTO) {
		String methodName = "createOrginization()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			adminService.createOrganization(createOrganizationFormDTO);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(EmployeePortalConstants.ERROR_MSG_METHOD_NAME_WITH_USER_NAME, methodName,
					createOrganizationFormDTO.getEmail(), errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					EmployeePortalConstants.ORGANIZATION_REGISTERED_SUCCESS_MESSAGE);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					EmployeePortalConstants.ORGANIZATION_REGISTERED_FAILED_MESSAGE, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

}
