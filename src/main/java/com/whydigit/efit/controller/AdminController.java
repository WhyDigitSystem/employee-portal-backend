package com.whydigit.efit.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.whydigit.efit.common.CommonConstant;
import com.whydigit.efit.common.EmployeePortalConstants;
import com.whydigit.efit.dto.CreateOrganizationFormDTO;
import com.whydigit.efit.dto.CreateUserFormDTO;
import com.whydigit.efit.dto.OrganizationDTO;
import com.whydigit.efit.dto.ResponseDTO;
import com.whydigit.efit.entity.BranchVO;
import com.whydigit.efit.entity.OrganizationVO;
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
@GetMapping("/getAllOrganization")
public ResponseEntity<ResponseDTO> getAllOrganization(){
		String methodName = "getAllOrganization()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<OrganizationVO> organizationVO =new ArrayList<>();
		try {
			organizationVO = adminService.getAllOrganization();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(EmployeePortalConstants.ERROR_MSG_METHOD_NAME_WITH_ORG_ID, methodName,
					 errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					EmployeePortalConstants.GET_ORGANIZATION_SUCCESS_MESSAGE);
			responseObjectsMap.put("organizationVO", organizationVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					EmployeePortalConstants.GET_ORGANIZATION_FAILED_MESSAGE, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return  ResponseEntity.ok().body(responseDTO);
	
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
	
	@PostMapping("/updateOrginization")
	public ResponseEntity<ResponseDTO> updateOrginization(@RequestBody OrganizationDTO organizationDTO) {
		String methodName = "updateOrginization()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		OrganizationVO organizationVO = new OrganizationVO();
		try {
			organizationVO = adminService.updateOrginization(organizationDTO);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(EmployeePortalConstants.ERROR_MSG_METHOD_NAME_WITH_ORG_NAME, methodName,
					organizationDTO.getName(), errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					EmployeePortalConstants.ORGANIZATION_UPDATE_SUCCESS_MESSAGE);
			responseObjectsMap.put("organizationVO", organizationVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					EmployeePortalConstants.ORGANIZATION_UPDATE_FAILED_MESSAGE, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@GetMapping("/orginization/{orgId}")
	public ResponseEntity<ResponseDTO> getOrginizationById(@PathVariable Long orgId) {
		String methodName = "getOrginizationById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		OrganizationVO organizationVO = new OrganizationVO();
		try {
			organizationVO = adminService.getOrginizationById(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(EmployeePortalConstants.ERROR_MSG_METHOD_NAME_WITH_ORG_ID, methodName,
					orgId, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					EmployeePortalConstants.ORGANIZATION_UPDATE_SUCCESS_MESSAGE);
			responseObjectsMap.put("organizationVO", organizationVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					EmployeePortalConstants.ORGANIZATION_UPDATE_FAILED_MESSAGE, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	@GetMapping("/getbranch/{branchId}")
	public ResponseEntity<ResponseDTO> getBranchById(@PathVariable Long branchId) {
		String methodName = "getBranchById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		BranchVO branchVO = new BranchVO();
		try {
			branchVO = adminService.getBranchById(branchId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(EmployeePortalConstants.ERROR_MSG_METHOD_NAME_WITH_ORG_ID, methodName,
					branchId, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					EmployeePortalConstants.GET_BRANCH_SUCCESS_MESSAGE);
			responseObjectsMap.put("branchVO", branchVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					EmployeePortalConstants.GET_BRANCH_FAILED_MESSAGE, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getbranch/{orgId}")
	public ResponseEntity<ResponseDTO> getBranchByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getBranchByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<BranchVO> branchVO = new ArrayList<>();
		try {
			branchVO = adminService.getBranchByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(EmployeePortalConstants.ERROR_MSG_METHOD_NAME_WITH_ORG_ID, methodName,
					orgId, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					EmployeePortalConstants.GET_BRANCH_BY_ORGID_SUCCESS_MESSAGE);
			responseObjectsMap.put("branchVO", branchVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					EmployeePortalConstants.GET_BRANCH_BY_ORGID_FAILED_MESSAGE, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	
	@PostMapping("/craeteBranch")
	public ResponseEntity<ResponseDTO> craeteBranch(@Valid @RequestBody BranchDTO branchDTO) {
		String methodName = "craeteBranch()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		BranchVO branchVO = new BranchVO();
		try {
			branchVO = adminService.craetebranch(branchDTO);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(EmployeePortalConstants.ERROR_MSG_METHOD_NAME_WITH_BRANCH_NAME, methodName,
					branchDTO.getBranchCode(), errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					EmployeePortalConstants.BRANCH_CREATE_SUCCESS_MESSAGE);
			responseObjectsMap.put("branchVO", branchVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					EmployeePortalConstants.BRANCH_CREATE_FAILED_MESSAGE, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@PostMapping("/updateBranch")
	public ResponseEntity<ResponseDTO> updateBranch(@Valid @RequestBody BranchDTO branchDTO) {
		String methodName = "updateBranch()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		BranchVO branchVO = new BranchVO();
		try {
			branchVO = adminService.craetebranch(branchDTO);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(EmployeePortalConstants.ERROR_MSG_METHOD_NAME_WITH_BRANCH_NAME, methodName,
					branchDTO.getBranchCode(), errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					EmployeePortalConstants.BRANCH_UPDATE_SUCCESS_MESSAGE);
			responseObjectsMap.put("branchVO", branchVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					EmployeePortalConstants.BRANCH_UPDATE_FAILED_MESSAGE, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	// get branch and Branch Code by Org Id
	@GetMapping("/getAllBranchbyOrgId")
	public ResponseEntity<ResponseDTO> getAllBranchByOrgId(@RequestParam Long orgId){
			String methodName = "getAllOrganization()";
			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;
			Set<Object[]> branch =new HashSet<>();
			try {
				branch = adminService.getBranchCodeBranchNameByOrgId(orgId);
			} catch (Exception e) {
				errorMsg = e.getMessage();
				LOGGER.error(EmployeePortalConstants.ERROR_MSG_METHOD_NAME_WITH_ORG_ID, methodName,
						 errorMsg);
			}
			if (StringUtils.isBlank(errorMsg)) {
				List<Map<String,String>>formattedBranch=formatbranch(branch);
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						EmployeePortalConstants.GET_BRANCH_BY_ORGID_SUCCESS_MESSAGE);
				responseObjectsMap.put("Branch", formattedBranch);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				responseDTO = createServiceResponseError(responseObjectsMap,
						EmployeePortalConstants.GET_BRANCH_BY_ORGID_FAILED_MESSAGE, errorMsg);
			}
			LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
			return  ResponseEntity.ok().body(responseDTO);
		
		}
	private List<Map<String, String>> formatbranch(Set<Object[]> branch) {
		List<Map<String, String>>formattedBranch=new ArrayList<>();
		for(Object[] branch1:branch) {
			Map<String, String>formattBranch=new HashMap<>();
			formattBranch.put("BranchId", branch1[0].toString());
			formattBranch.put("BranchCode", branch1[1].toString());
			formattBranch.put("BranchName", branch1[2].toString());
			formattedBranch.add(formattBranch);
		}
		return formattedBranch;
	}
}
