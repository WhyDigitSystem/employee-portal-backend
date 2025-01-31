package com.whydigit.efit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.whydigit.efit.dto.ExcelUploadResultDTO;
import com.whydigit.efit.dto.MonthlyAttendanceDTO;
import com.whydigit.efit.dto.ResponseDTO;
import com.whydigit.efit.entity.SalaryMasterVO;
import com.whydigit.efit.service.SalaryMasterService;

@RestController
@CrossOrigin
@RequestMapping("/api/salaryMaster")
public class SalaryMasterController extends BaseController {

	public static final Logger LOGGER = LoggerFactory.getLogger(SalaryMasterController.class);

	@Autowired
	SalaryMasterService edService;

	@GetMapping("/getAllSalaryMaster")
	public List<SalaryMasterVO> getAllSalaryMaster() {
		return edService.getAllSalaryMaster();
	}

	@GetMapping("/getAllSalaryMasterById")
	public SalaryMasterVO getAllSalaryMasterById(@RequestParam Long id) {
		return edService.getAllSalaryMasterById(id);
	}

	@PostMapping("/createSalaryMaster")
	public SalaryMasterVO createSalaryMaster(@RequestBody SalaryMasterVO salaryMaster) {
		return edService.createSalaryMaster(salaryMaster);
	}

	@PutMapping("/updateSalaryMaster")
	public SalaryMasterVO updateSalaryMaster(@RequestBody SalaryMasterVO salaryMaster, @RequestParam Long id) {
		return edService.updateSalaryMaster(salaryMaster, id);
	}
	
	@PutMapping("/createMonthlyAttendance")
	public ResponseEntity<ResponseDTO> createMonthlyAttendance(@RequestBody List<MonthlyAttendanceDTO> monthlyAttendanceDTO) {
		String methodName = "createMonthlyAttendance()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> monthlyAttendanceDetails = edService.createMonthlyAttendance(monthlyAttendanceDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, monthlyAttendanceDetails.get("message"));
			responseObjectsMap.put("monthlyAttendanceDetails", monthlyAttendanceDetails.get("monthlyAttendanceDetails"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	@PostMapping("/excelUploadForMonthlyAttendance")
	public ResponseEntity<ResponseDTO> excelUploadForMonthlyAttendance(@RequestParam MultipartFile[] files,
			@RequestParam Long orgId) {

		String methodName = "excelUploadForMonthlyAttendance()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);

		ResponseDTO responseDTO;
		Map<String, Object> responseObjectsMap = new HashMap<>();

		try {
			// Call the service method and get the result
			ExcelUploadResultDTO uploadResult = edService.excelUploadForMonthlyAttendance(files,orgId);

			responseObjectsMap.put("status", uploadResult.getFailureReasons().isEmpty());
			// Populate success response
			responseObjectsMap.put("statusFlag", "Ok");
			responseObjectsMap.put("uploadResult", uploadResult);
			responseDTO = createServiceResponse(responseObjectsMap);

		} catch (Exception e) {
			// Handle any exceptions and populate error response
			LOGGER.error(CommonConstant.EXCEPTION, methodName, e);

			responseObjectsMap.put("statusFlag", "Error");
			responseObjectsMap.put("status", false);
			responseObjectsMap.put("errorMessage", e.getMessage());

			responseDTO = createServiceResponseError(responseObjectsMap, "Excel Upload For Client COA Failed",
					e.getMessage());
		}

		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
}