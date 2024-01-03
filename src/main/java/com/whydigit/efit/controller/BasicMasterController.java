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
import com.whydigit.efit.entity.CityVO;
import com.whydigit.efit.entity.CountryListVO;
import com.whydigit.efit.entity.CountryVO;
import com.whydigit.efit.entity.CurrencyMasterVO;
import com.whydigit.efit.entity.EmployeeVO;
import com.whydigit.efit.entity.FinancialYearVO;
import com.whydigit.efit.entity.LocalCurrencyVO;
import com.whydigit.efit.entity.StateVO;
import com.whydigit.efit.service.BasicMasterService;


@RestController
@CrossOrigin
@RequestMapping("/api/basicMaster")
public class BasicMasterController extends BaseController {

	public static final Logger LOGGER = LoggerFactory.getLogger(BasicMasterController.class);

	@Autowired
	private BasicMasterService basicMasterService;

	@GetMapping("/currency")
	public ResponseEntity<ResponseDTO> getAllLocalCurrencies() {
		String methodName = "getAllLocalCurrencies()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<LocalCurrencyVO> localCurrencyVO = new ArrayList<>();
		try {
			localCurrencyVO = basicMasterService.getAllLocalCurrencies();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "localCurrency information get successfully");
			responseObjectsMap.put("localCurrencies", localCurrencyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "localCurrency information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/currency/{id}")
	public ResponseEntity<ResponseDTO> getLocalCurrencyById(@PathVariable int id) {
		String methodName = "getLocalCurrencyById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		LocalCurrencyVO localCurrencyVO = null;
		try {
			localCurrencyVO = basicMasterService.getLocalCurrencyById(id).orElse(null);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "localCurrency found by ID");
			responseObjectsMap.put("localCurrency", localCurrencyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "LocalCurrency not found for ID: " + id;
			responseDTO = createServiceResponseError(responseObjectsMap, "LocalCurrency not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping("/currency")
	public ResponseEntity<ResponseDTO> createLocalCurrency(@RequestBody LocalCurrencyVO localCurrencyVO) {
		String methodName = "createLocalCurrency()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			LocalCurrencyVO createdLocalCurrency = basicMasterService.createLocalCurrency(localCurrencyVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "LocalCurrency created successfully");
			responseObjectsMap.put("localCurrency", createdLocalCurrency);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "LocalCurrency creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/currency")
	public ResponseEntity<ResponseDTO> updateLocalCurrency(@RequestBody LocalCurrencyVO localCurrencyVO) {
		String methodName = "updateLocalCurrency()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			LocalCurrencyVO updatedLocalCurrency = basicMasterService.updateLocalCurrency(localCurrencyVO).orElse(null);
			if (updatedLocalCurrency != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "LocalCurrency updated successfully");
				responseObjectsMap.put("localCurrency", updatedLocalCurrency);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "LocalCurrency not found for ID: " + localCurrencyVO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "LocalCurrency update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "LocalCurrency update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@DeleteMapping("/currency/{id}")
	public ResponseEntity<ResponseDTO> deleteLocalCurrency(@PathVariable int id) {
		String methodName = "deleteLocalCurrency()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			basicMasterService.deleteLocalCurrency(id);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "LocalCurrency deleted successfully");
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "LocalCurrency deletion failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/country")
	public ResponseEntity<ResponseDTO> getAllcountries() {
		String methodName = "getAllCountry()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CountryVO> countryVO = new ArrayList<>();
		try {
			countryVO = basicMasterService.getAllgetAllcountries();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "countries information get successfully");
			responseObjectsMap.put("countryVO", countryVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "countries information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/country/{id}")
	public ResponseEntity<ResponseDTO> getCountryById(@PathVariable int id) {
		String methodName = "getCountryById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		CountryVO countryVO = null;
		try {
			countryVO = basicMasterService.getCountryById(id).orElse(null);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Countries found by ID");
			responseObjectsMap.put("countryVO", countryVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "Countries not found for ID: " + id;
			responseDTO = createServiceResponseError(responseObjectsMap, "Countries not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping("/country")
	public ResponseEntity<ResponseDTO> createCountry(@RequestBody CountryVO countryVO) {
		String methodName = "createCountry()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CountryVO createdCountryVO = basicMasterService.createCountry(countryVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Country created successfully");
			responseObjectsMap.put("countryVO", createdCountryVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Country creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/country")
	public ResponseEntity<ResponseDTO> updateCountry(@RequestBody CountryVO countryVO) {
		String methodName = "updateCountry()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CountryVO updatedCountryVO = basicMasterService.updateCountry(countryVO).orElse(null);
			if (updatedCountryVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Country updated successfully");
				responseObjectsMap.put("countryVO", updatedCountryVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Country not found for ID: " + countryVO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Country update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Country update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@DeleteMapping("/country/{id}")
	public ResponseEntity<ResponseDTO> deleteCountry(@PathVariable int id) {
		String methodName = "deleteCountry()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			basicMasterService.deleteCountry(id);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Country deleted successfully");
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Country deletion failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	state



	@GetMapping("/state")
	public ResponseEntity<ResponseDTO> getAllStates() {
		String methodName = "getAllStates()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<StateVO> stateVO = new ArrayList<>();
		try {
			stateVO = basicMasterService.getAllgetAllStates();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "state information get successfully");
			responseObjectsMap.put("stateVO", stateVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "states information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
//
//	@GetMapping("/state/{id}")
//	public ResponseEntity<ResponseDTO> getStateById(@PathVariable int id) {
//		String methodName = "getStateById()";
//		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
//		String errorMsg = null;
//		Map<String, Object> responseObjectsMap = new HashMap<>();
//		ResponseDTO responseDTO = null;
//		StateVO stateVO = null;
//		try {
//			stateVO = basicMasterService.getStateById(id).orElse(null);
//		} catch (Exception e) {
//			errorMsg = e.getMessage();
//			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
//		}
//		if (StringUtils.isEmpty(errorMsg)) {
//			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "states found by ID");
//			responseObjectsMap.put("stateVO", stateVO);
//			responseDTO = createServiceResponse(responseObjectsMap);
//		} else {
//			errorMsg = "Countries not found for ID: " + id;
//			responseDTO = createServiceResponseError(responseObjectsMap, "states not found", errorMsg);
//		}
//		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
//		return ResponseEntity.ok().body(responseDTO);
//	}

	@PostMapping("/state")
	public ResponseEntity<ResponseDTO> createState(@RequestBody StateVO stateVO) {
		String methodName = "createState()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			StateVO createdStateVO = basicMasterService.createState(stateVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "state created successfully");
			responseObjectsMap.put("stateVO", createdStateVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "state creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/state")
	public ResponseEntity<ResponseDTO> updateState(@RequestBody StateVO stateVO) {
		String methodName = "updateState()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			StateVO updatedStateVO = basicMasterService.updateState(stateVO).orElse(null);
			if (updatedStateVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "state updated successfully");
				responseObjectsMap.put("countryVO", updatedStateVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "State not found for ID: " + stateVO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "state update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "state update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@DeleteMapping("/state/{id}")
	public ResponseEntity<ResponseDTO> deleteState(@PathVariable int id) {
		String methodName = "deleteState()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			basicMasterService.deleteState(id);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "state deleted successfully");
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "state deletion failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}



//	employee

	@GetMapping("/employee")
	public ResponseEntity<ResponseDTO> getAllEmployees() {
		String methodName = "getAllEmployees()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<EmployeeVO> employeeVO = new ArrayList<>();
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
	public ResponseEntity<ResponseDTO> createEmployee(@RequestBody EmployeeVO employeeVO) {
		String methodName = "createEmployee()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			EmployeeVO createdEmployeeVO = basicMasterService.createEmployee(employeeVO);
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
	public ResponseEntity<ResponseDTO> updateEmployee(@RequestBody EmployeeVO employeeVO) {
		String methodName = "updateEmployee()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			EmployeeVO updatedEmployeeVO = basicMasterService.updateEmployee(employeeVO).orElse(null);
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



//	city

	@GetMapping("/city")
	public ResponseEntity<ResponseDTO> getAllCities() {
		String methodName = "getAllCities()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CityVO> cityVO = new ArrayList<>();
		try {
			cityVO = basicMasterService.getAllgetAllCities();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "city information get successfully");
			responseObjectsMap.put("cityVO", cityVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "city information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping("/city")
	public ResponseEntity<ResponseDTO> createCity(@RequestBody CityVO cityVO) {
		String methodName = "createCity()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CityVO createdCityVO = basicMasterService.createCity(cityVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "city created successfully");
			responseObjectsMap.put("cityVO", createdCityVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "city creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	

	@PutMapping("/city")
	public ResponseEntity<ResponseDTO> updateCity(@RequestBody CityVO cityVO) {
		String methodName = "updateCity()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CityVO updatedCityVO = basicMasterService.updateCity(cityVO).orElse(null);
			if (updatedCityVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "City updated successfully");
				responseObjectsMap.put("cityVO", updatedCityVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "City not found for ID: " + cityVO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "City update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "City update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@DeleteMapping("/city/{id}")
	public ResponseEntity<ResponseDTO> deleteCity(@PathVariable int id) {
		String methodName = "deleteCity()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			basicMasterService.deleteCity(id);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "city deleted successfully");
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "city deletion failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	Financial

	@GetMapping("/financial")
	public ResponseEntity<ResponseDTO> getAllFinancialYears() {
		String methodName = "getAllFinancialYears()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<FinancialYearVO> financialYearVO = new ArrayList<>();
		try {
			financialYearVO = basicMasterService.getAllgetAllFinancialYears();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Financial Year information get successfully");
			responseObjectsMap.put("financialYearVO", financialYearVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "FinancialYear information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping("/financial")
	public ResponseEntity<ResponseDTO> createFinancialYear(@RequestBody FinancialYearVO financialYearVO) {
		String methodName = "createFinancialYear()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			FinancialYearVO createdFinancialYearVO = basicMasterService.createFinancialYear(financialYearVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "financialYear created successfully");
			responseObjectsMap.put("financialYearVO", createdFinancialYearVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "financial Year creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}


	@PutMapping("/financial")
	public ResponseEntity<ResponseDTO> updateFinancialYearVO(@RequestBody FinancialYearVO financialYearVO) {
		String methodName = "updateFinancialYear()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			FinancialYearVO updatedFinancialYearVO = basicMasterService.updateFinancialYear(financialYearVO).orElse(null);
			if (updatedFinancialYearVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "FinancialYear updated successfully");
				responseObjectsMap.put("financialYearVO", updatedFinancialYearVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "FinancialYear not found for ID: " + financialYearVO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Employee update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "FinancialYear update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@DeleteMapping("/financial/{id}")
	public ResponseEntity<ResponseDTO> deleteFinancialYear(@PathVariable int id) {
		String methodName = "deleteFinancialYear()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			basicMasterService.deleteFinancialYear(id);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "financial year deleted successfully");
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "financial Year deletion failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
//	countryList
	
	@GetMapping("/countryList")
	public ResponseEntity<ResponseDTO> getAllCountryList() {
		String methodName = "getAllCountryList()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CountryListVO> countryListVO = new ArrayList<>();
		try {
			countryListVO = basicMasterService.getAllCountryList();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "CountryList information get successfully");
			responseObjectsMap.put("CountryList", countryListVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "CountryList information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/countryList/{id}")
	public ResponseEntity<ResponseDTO> getCountryListById(@PathVariable int id) {
		String methodName = "getCountryListById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		CountryListVO countryListVO = null;
		try {
			countryListVO = basicMasterService.getCountryListById(id).orElse(null);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "localCurrency found by ID");
			responseObjectsMap.put("countryList", countryListVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "CountrytList not found for ID: " + id;
			responseDTO = createServiceResponseError(responseObjectsMap, "countrylist not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping("/CountryList")
	public ResponseEntity<ResponseDTO> createCountryList(@RequestBody CountryListVO countryListVO) {
		String methodName = "createCountryList()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CountryListVO createdCountryList = basicMasterService.createCountryList(countryListVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "countryList created successfully");
			responseObjectsMap.put("countryList", createdCountryList);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "CountryList creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/countryList")
	public ResponseEntity<ResponseDTO> updateCountryList(@RequestBody CountryListVO countryListVO) {
		String methodName = "updateCountryList()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CountryListVO updatedCountryList = basicMasterService.updateCountryList(countryListVO).orElse(null);
			if (updatedCountryList != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "CountryList updated successfully");
				responseObjectsMap.put("countryList", updatedCountryList);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "CountryList not found for ID: " + countryListVO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "CountryList update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "CountryList update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@DeleteMapping("/countryList/{id}")
	public ResponseEntity<ResponseDTO> deleteCountryList(@PathVariable int id) {
		String methodName = "deleteCountryList()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			basicMasterService.deleteCountryList(id);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "CountryList deleted successfully");
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "CountryList deletion failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
//	Financial

	@GetMapping("/currencyMaster")
	public ResponseEntity<ResponseDTO> getAllCurrencyMaster() {
		String methodName = "getAllCurrencyMaster()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CurrencyMasterVO> currencyMasterVO = new ArrayList<>();
		try {
			currencyMasterVO = basicMasterService.getAllCurrencyMaster();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Currency master information get successfully");
			responseObjectsMap.put("currencyMasterVO", currencyMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "currencyMaster information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping("/currencyMaster")
	public ResponseEntity<ResponseDTO> createCurrencyMaster(@RequestBody CurrencyMasterVO currencyMasterVO) {
		String methodName = "createCurrencyMaster()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CurrencyMasterVO createdCurrencyMasterVO = basicMasterService.createCurrencyMasterVO(currencyMasterVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "CurrencyMaster created successfully");
			responseObjectsMap.put("currencyMasterVO", createdCurrencyMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "CurrencyMaster Year creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}


	@PutMapping("/currencyMaster")
	public ResponseEntity<ResponseDTO> updateCurrencyMasterVO(@RequestBody CurrencyMasterVO currencyMasterVO) {
		String methodName = "updateCurrencyMaster()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CurrencyMasterVO updatedCurrencyMasterVO = basicMasterService.updateCurrencyMaster(currencyMasterVO).orElse(null);
			if (updatedCurrencyMasterVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "CurrencyMasterVO updated successfully");
				responseObjectsMap.put("currencyMasterVO", updatedCurrencyMasterVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "CurrencyMaster not found for ID: " + currencyMasterVO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "CurrencyMaster update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "CurrencyMaster update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@DeleteMapping("/currencyMaster/{id}")
	public ResponseEntity<ResponseDTO> deleteCurrencyMaster(@PathVariable int id) {
		String methodName = "deleteCurrencyMaster()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			basicMasterService.deleteCurrencyMaster(id);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "CurrencyMaster year deleted successfully");
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "CurrencyMaster deletion failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
}
