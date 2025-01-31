package com.whydigit.efit.service;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.whydigit.efit.dto.EmployeeDTO;
import com.whydigit.efit.dto.LeaveDetailsDTO;
import com.whydigit.efit.dto.RequestLeaveDTO;
import com.whydigit.efit.dto.Role;
import com.whydigit.efit.entity.CheckinVO;
import com.whydigit.efit.entity.EmployeeLeaveDetailsVO;
import com.whydigit.efit.entity.EmployeeVO;
import com.whydigit.efit.entity.LeaveDetailsVO;
import com.whydigit.efit.entity.RequestLeaveVO;
import com.whydigit.efit.entity.UserVO;
import com.whydigit.efit.exception.ApplicationException;
import com.whydigit.efit.repo.CheckinRepo;
import com.whydigit.efit.repo.EmployeeLeaveDetailsRepo;
import com.whydigit.efit.repo.EmployeeRepo;
import com.whydigit.efit.repo.LeaveDetailsRepo;
import com.whydigit.efit.repo.RequestLeaveRepo;
import com.whydigit.efit.repo.UserRepo;

@Service
public class MasterControllerServiceImpl implements MasterControllerService {

	@Autowired
	LeaveDetailsRepo leaveDetailsRepo;

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	EmployeeLeaveDetailsRepo employeeLeaveDetailsRepo;

	@Autowired
	RequestLeaveRepo requestLeaveRepo;

	@Autowired
	CheckinRepo checkinRepo;

	public static final Logger LOGGER = LoggerFactory.getLogger(MasterControllerServiceImpl.class);

	@Override
	public Map<String, Object> createUpdateLeaveDeatils(LeaveDetailsDTO leaveDetailsDTO) throws ApplicationException {

		LeaveDetailsVO leaveDetailsVO = null;
		String message = null;

		if (ObjectUtils.isEmpty(leaveDetailsDTO.getId())) {

			if (leaveDetailsRepo.existsByLeaveTypeAndOrgId(leaveDetailsDTO.getLeaveType(),
					leaveDetailsDTO.getOrgId())) {
				String errorMessage = String.format("Leave Type Already Exists in This Organization: %s",
						leaveDetailsDTO.getLeaveType());

				throw new ApplicationException(errorMessage);
			}

			if (leaveDetailsRepo.existsByLeaveCodeAndOrgId(leaveDetailsDTO.getLeaveCode(),
					leaveDetailsDTO.getOrgId())) {

				String errorMessage = String.format("Leave Code Already Exists in This Organization: %s",
						leaveDetailsDTO.getLeaveCode());

				throw new ApplicationException(errorMessage);
			}

			leaveDetailsVO = new LeaveDetailsVO();
			leaveDetailsVO.setCreatedBy(leaveDetailsDTO.getCreatedBy());
			leaveDetailsVO.setUpdatedBy(leaveDetailsDTO.getCreatedBy());

			message = "Leave Details Created Successfully";
		} else {

			leaveDetailsVO = leaveDetailsRepo.findById(leaveDetailsDTO.getId()).orElseThrow(
					() -> new ApplicationException("Leave Details Not Found with ID: " + leaveDetailsDTO.getId()));

			if (!leaveDetailsVO.getLeaveType().equalsIgnoreCase(leaveDetailsDTO.getLeaveType())) {
				if (leaveDetailsRepo.existsByLeaveTypeAndOrgId(leaveDetailsDTO.getLeaveType(),
						leaveDetailsDTO.getOrgId())) {
					String errorMessage = String.format("Leave Type Already Exists in This Organization: %s",
							leaveDetailsDTO.getLeaveType());
					throw new ApplicationException(errorMessage);
				}
				leaveDetailsVO.setLeaveType(leaveDetailsDTO.getLeaveType());
			}
			if (!leaveDetailsVO.getLeaveCode().equalsIgnoreCase(leaveDetailsDTO.getLeaveCode())) {
				if (leaveDetailsRepo.existsByLeaveCodeAndOrgId(leaveDetailsDTO.getLeaveCode(),
						leaveDetailsDTO.getOrgId())) {
					String errorMessage = String.format("Leave Code Already Exists in This Organization: %s",
							leaveDetailsDTO.getLeaveCode());
					throw new ApplicationException(errorMessage);
				}
				leaveDetailsVO.setLeaveCode(leaveDetailsDTO.getLeaveCode());
			}
		}

		// Update leave counts in EmployeeLeaveDetailsVO if necessary
		updateEmployeeLeaveCounts(leaveDetailsDTO);

		leaveDetailsVO = getLeaveDetailsVOFromLeaveDetailsDTO(leaveDetailsVO, leaveDetailsDTO);
		leaveDetailsRepo.save(leaveDetailsVO);

		Map<String, Object> response = new HashMap<>();
		response.put("leaveDetailsVO", leaveDetailsVO);
		response.put("message", message);
		return response;
	}

	private void updateEmployeeLeaveCounts(LeaveDetailsDTO leaveDetailsDTO) {
		List<EmployeeLeaveDetailsVO> employeeLeaveDetailsList = employeeLeaveDetailsRepo
				.findByOrgId(leaveDetailsDTO.getOrgId());

		for (EmployeeLeaveDetailsVO employeeLeaveDetailsVO : employeeLeaveDetailsList) {
			int newCount = leaveDetailsDTO.getNoOfDays();
			switch (leaveDetailsDTO.getLeaveType()) {
			case "Annual Leave":
				if (employeeLeaveDetailsVO.getAl() != newCount) {
					employeeLeaveDetailsVO.setAl(newCount);
				}
				break;
			case "Casual Leave":
				if (employeeLeaveDetailsVO.getCl() != newCount) {
					employeeLeaveDetailsVO.setCl(newCount);
				}
				break;
			case "Compensatory Off":
				if (employeeLeaveDetailsVO.getCo() != newCount) {
					employeeLeaveDetailsVO.setCo(newCount);
				}
				break;
			case "Maternity Leave":
				if (employeeLeaveDetailsVO.getMl() != newCount) {
					employeeLeaveDetailsVO.setMl(newCount);
				}
				break;
			case "Paternity Leave":
				if (employeeLeaveDetailsVO.getPl() != newCount) {
					employeeLeaveDetailsVO.setPl(newCount);
				}
				break;
			case "Sick Leave":
				if (employeeLeaveDetailsVO.getSl() != newCount) {
					employeeLeaveDetailsVO.setSl(newCount);
				}
				break;
			case "Unpaid Leave":
				if (employeeLeaveDetailsVO.getUl() != newCount) {
					employeeLeaveDetailsVO.setUl(newCount);
				}
				break;
			default:
				System.out.println("Unknown leave type: " + leaveDetailsDTO.getLeaveType());
			}

			// Save updated EmployeeLeaveDetailsVO
			employeeLeaveDetailsRepo.save(employeeLeaveDetailsVO);
		}
	}

	private LeaveDetailsVO getLeaveDetailsVOFromLeaveDetailsDTO(LeaveDetailsVO leaveDetailsVO,
			LeaveDetailsDTO leaveDetailsDTO) {
		leaveDetailsVO.setLeaveCode(leaveDetailsDTO.getLeaveCode());
		leaveDetailsVO.setLeaveType(leaveDetailsDTO.getLeaveType());
		leaveDetailsVO.setNoOfDays(leaveDetailsDTO.getNoOfDays());
		leaveDetailsVO.setEffective(leaveDetailsDTO.getEffective());
		leaveDetailsVO.setOrgId(leaveDetailsDTO.getOrgId());
		leaveDetailsVO.setActive(leaveDetailsDTO.isActive());
		leaveDetailsVO.setLeaveApplicable(leaveDetailsDTO.getLeaveApplicable());
		leaveDetailsVO.setCarryForward(leaveDetailsDTO.getCarryForward());

		return leaveDetailsVO;
	}

	@Override
	public List<LeaveDetailsVO> getAllLeaveDetails(Long orgId) {
		return leaveDetailsRepo.getAllLeaveDetailsByOrgId(orgId);
	}

	@Override
	public Optional<LeaveDetailsVO> getAllLeaveDetailsById(Long id) {
		return leaveDetailsRepo.findById(id);
	}

	// NEW EMPLOYEE CREATION

	@Override
	public Map<String, Object> createUpdateEmployee(EmployeeDTO employeeDTO) throws ApplicationException {
		EmployeeVO employeeVO;
		String message;

		// Check if creating a new employee or updating an existing one
		if (ObjectUtils.isEmpty(employeeDTO.getId())) {
			// Validate uniqueness of employee details for new creation
			validateEmployeeUniqueness(employeeDTO);

			employeeVO = new EmployeeVO();
			employeeVO.setCreatedBy(employeeDTO.getCreatedBy());
			employeeVO.setUpdatedby(employeeDTO.getCreatedBy()); // Set updatedBy to createdBy when creating
			message = "Employee created successfully";
		} else {
			// Fetch existing employee for update
			employeeVO = employeeRepo.findById(employeeDTO.getId()).orElseThrow(
					() -> new ApplicationException("Employee details not found with ID: " + employeeDTO.getId()));

			// Validate uniqueness only if the fields are being updated
			validateAndUpdateEmployeeFields(employeeVO, employeeDTO);
			message = "Employee updated successfully";
		}

		// Map DTO to VO
		employeeVO = getEmployeeVOFromEmployeeDTO(employeeVO, employeeDTO);

		// Save employee (ensure this is only done once)
		employeeRepo.save(employeeVO);

		// Create or update user details
		createOrUpdateUser(employeeVO, employeeDTO);

		// Create or update leave details
		createOrUpdateEmployeeLeaveDetails(employeeVO, employeeDTO);

		// Prepare response
		Map<String, Object> response = new HashMap<>();
		response.put("employeeVO", employeeVO);
		response.put("message", message);
		return response;
	}

	// Validate uniqueness for new employees
	private void validateEmployeeUniqueness(EmployeeDTO employeeDTO) throws ApplicationException {
		if (employeeRepo.existsByEmpCodeAndOrgId(employeeDTO.getEmpCode(), employeeDTO.getOrgId())) {
			throw new ApplicationException(
					String.format("This EmpCode '%s' already exists in this organization", employeeDTO.getEmpCode()));
		}
		if (employeeRepo.existsByEmailAndOrgId(employeeDTO.getEmail(), employeeDTO.getOrgId())) {
			throw new ApplicationException(
					String.format("This Email '%s' already exists in this organization", employeeDTO.getEmail()));
		}
		if (employeeRepo.existsByMobileNoAndOrgId(employeeDTO.getMobileNo(), employeeDTO.getOrgId())) {
			throw new ApplicationException(
					String.format("This MobileNo '%s' already exists in this organization", employeeDTO.getMobileNo()));
		}
		if (employeeRepo.existsByAadharAndOrgId(employeeDTO.getAadhar(), employeeDTO.getOrgId())) {
			throw new ApplicationException(
					String.format("This Aadhar '%s' already exists in this organization", employeeDTO.getAadhar()));
		}
		if (employeeRepo.existsByAccountNoAndOrgId(employeeDTO.getAccountNo(), employeeDTO.getOrgId())) {
			throw new ApplicationException(String.format("This AccountNo '%s' already exists in this organization",
					employeeDTO.getAccountNo()));
		}
	}

	// Validate and update fields for existing employees
	private void validateAndUpdateEmployeeFields(EmployeeVO employeeVO, EmployeeDTO employeeDTO)
			throws ApplicationException {
		if (!employeeVO.getEmpCode().equalsIgnoreCase(employeeDTO.getEmpCode())) {
			if (employeeRepo.existsByEmpCodeAndOrgId(employeeDTO.getEmpCode(), employeeDTO.getOrgId())) {
				throw new ApplicationException(String.format("This EmpCode '%s' already exists in this organization",
						employeeDTO.getEmpCode()));
			}
			employeeVO.setEmpCode(employeeDTO.getEmpCode());
		}
		if (!employeeVO.getEmail().equalsIgnoreCase(employeeDTO.getEmail())) {
			if (employeeRepo.existsByEmailAndOrgId(employeeDTO.getEmail(), employeeDTO.getOrgId())) {
				throw new ApplicationException(
						String.format("This Email '%s' already exists in this organization", employeeDTO.getEmail()));
			}
			employeeVO.setEmail(employeeDTO.getEmail());
		}
		// Additional validations for mobile, aadhar, and account number...
	}

	// Map EmployeeDTO to EmployeeVO
	private EmployeeVO getEmployeeVOFromEmployeeDTO(EmployeeVO employeeVO, EmployeeDTO employeeDTO) {
		employeeVO.setOrgId(employeeDTO.getOrgId());
		employeeVO.setEmpCode(employeeDTO.getEmpCode());
		employeeVO.setEmpName(employeeDTO.getEmpName());
		employeeVO.setGender(employeeDTO.getGender());
		employeeVO.setDateOfBirth(employeeDTO.getDateOfBirth());
		employeeVO.setBlood(employeeDTO.getBlood());
		employeeVO.setDepartment(employeeDTO.getDepartment());
		employeeVO.setDesignation(employeeDTO.getDesignation());
		employeeVO.setRole(employeeDTO.getRole());
		employeeVO.setAlternateMobileNo(employeeDTO.getAlternateMobileNo());
		employeeVO.setCompanyCode(employeeDTO.getCompanyCode());
		employeeVO.setBranchId(employeeDTO.getBranchId());
		employeeVO.setEmail(employeeDTO.getEmail());
		employeeVO.setJoiningDate(employeeDTO.getJoiningDate());
		employeeVO.setResigningDate(employeeDTO.getResigningDate());
		employeeVO.setUserType(employeeDTO.getUserType());
		employeeVO.setMobileNo(employeeDTO.getMobileNo());
		employeeVO.setPan(employeeDTO.getPan());
		employeeVO.setAadhar(employeeDTO.getAadhar());
		employeeVO.setBankName(employeeDTO.getBankName());
		employeeVO.setAccountNo(employeeDTO.getAccountNo());
		employeeVO.setIfscCode(employeeDTO.getIfscCode());
		employeeVO.setReportingPerson(employeeDTO.getReportingPerson());
		employeeVO.setActive(employeeDTO.isActive());
		employeeVO.setRemarks(employeeDTO.getRemarks());
		employeeVO.setReportingPersonRole(employeeDTO.getReportingPersonRole());
		return employeeVO;
	}

	// Create or update user details
	private void createOrUpdateUser(EmployeeVO employeeVO, EmployeeDTO employeeDTO) {
		// Check if the user already exists for the employee
		UserVO existingUser = userRepo.findByEmpId(employeeVO.getId());

		if (existingUser != null) {
			// If user exists, update the user details
			existingUser.setActive(employeeDTO.isActive());
			existingUser.setEmail(employeeDTO.getEmail());
			existingUser.setEmpcode(employeeDTO.getEmpCode());
			existingUser.setEmpname(employeeDTO.getEmpName());
			existingUser.setOrgId(employeeDTO.getOrgId());
			existingUser.setRole(Role.USER);
			// Update password if needed (be careful not to overwrite passwords
			// unnecessarily)
			// existingUser.setPassword(existingPassword); // Only if you need to change the
			// password

			userRepo.save(existingUser); // Save the updated user
		} else {
			// If user doesn't exist, create a new user
			UserVO newUser = new UserVO();
			newUser.setActive(employeeDTO.isActive());
			newUser.setEmail(employeeDTO.getEmail());
			newUser.setEmpcode(employeeDTO.getEmpCode());
			newUser.setEmpname(employeeDTO.getEmpName());
			newUser.setEmpId(employeeVO.getId());
			newUser.setOrgId(employeeDTO.getOrgId());
			newUser.setRole(Role.USER);
			newUser.setPassword("$2a$10$NWMg1GT3w0KnuZowQcUObOmJoivV.abut6ZcJ0u3DdaFYYKMUgeA2"); // Default hashed
																									// password
			userRepo.save(newUser); // Save the new user
		}
	}

	private void createOrUpdateEmployeeLeaveDetails(EmployeeVO employeeVO, EmployeeDTO employeeDTO)
			throws ApplicationException {
		// Fetch or create new employee leave details
		EmployeeLeaveDetailsVO employeeLeaveDetailsVO = employeeLeaveDetailsRepo
				.findByEmployeeVOAndOrgId(employeeVO, employeeVO.getOrgId()).orElseGet(() -> {
					EmployeeLeaveDetailsVO newDetails = new EmployeeLeaveDetailsVO();
					newDetails.setEmployeeVO(employeeVO);
					newDetails.setOrgId(employeeVO.getOrgId());
					newDetails.setGender(employeeVO.getGender());
					return newDetails;
				});

		// Fetch leave details for the organization
		List<LeaveDetailsVO> leaveDetailsList = leaveDetailsRepo.findByOrgId(employeeDTO.getOrgId());

		// Check if leave details exist for the given organization
		if (leaveDetailsList == null || leaveDetailsList.isEmpty()) {
			throw new ApplicationException("No leave details found for Org ID: " + employeeDTO.getOrgId());
		}

		// Process each leave type for the employee
		for (LeaveDetailsVO leaveDetailsVO : leaveDetailsList) {
			// Skip leave type if not applicable based on gender or leave condition
			if (!"all".equalsIgnoreCase(leaveDetailsVO.getLeaveApplicable())
					&& !leaveDetailsVO.getLeaveApplicable().equalsIgnoreCase(employeeVO.getGender())) {
				continue;
			}

			// Set leave balance based on leave type
			switch (leaveDetailsVO.getLeaveType()) {
			case "Annual Leave":
				employeeLeaveDetailsVO.setAl(leaveDetailsVO.getNoOfDays());
				break;
			case "Casual Leave":
				employeeLeaveDetailsVO.setCl(leaveDetailsVO.getNoOfDays());
				break;
			case "Compensatory Off":
				employeeLeaveDetailsVO.setCo(leaveDetailsVO.getNoOfDays());
				break;
			case "Maternity Leave":
				employeeLeaveDetailsVO.setMl(leaveDetailsVO.getNoOfDays());
				break;
			case "Paternity Leave":
				employeeLeaveDetailsVO.setPl(leaveDetailsVO.getNoOfDays());
				break;
			case "Sick Leave":
				employeeLeaveDetailsVO.setSl(leaveDetailsVO.getNoOfDays());
				break;
			case "Unpaid Leave":
				employeeLeaveDetailsVO.setUl(leaveDetailsVO.getNoOfDays());
				break;
			default:
				// Handle any unknown leave type (use default case)
				System.out.println("Unknown leave type: " + leaveDetailsVO.getLeaveType());
				break;
			}

			// Always set 'effective' and 'carryForward' for each leave type if available
			if (leaveDetailsVO.getEffective() != null) {
				employeeLeaveDetailsVO.setEffective(leaveDetailsVO.getEffective());
			}

			if (leaveDetailsVO.getCarryForward() != null) {
				employeeLeaveDetailsVO.setCarryForward(leaveDetailsVO.getCarryForward());
			}
		}

		// Save the updated employee leave details
		employeeLeaveDetailsRepo.save(employeeLeaveDetailsVO);
	}

	@Override
	public Optional<EmployeeVO> getEmployeeById(Long id) {
		return employeeRepo.findById(id);
	}

	@Override
	public List<EmployeeVO> getAllEmployee(Long orgId) {
		return employeeRepo.getAllEmployees(orgId);
	}

	@Override
	public List<Map<String, Object>> getEmployeeLeaveCount(Long orgId, Long empId) {

		Set<Object[]> getEmployeeLeave = employeeRepo.findEmployeeLeaveCount(orgId, empId);
		return getPayment(getEmployeeLeave);
	}

	private List<Map<String, Object>> getPayment(Set<Object[]> getPayment) {
		List<Map<String, Object>> employeeLeaveDetails = new ArrayList<>();
		for (Object[] sup : getPayment) {
			Map<String, Object> getCount = new HashMap<>();
			getCount.put("Annual Leave", sup[0] != null ? sup[0].toString() : "");
			getCount.put("Casual Leave", sup[1] != null ? sup[1].toString() : "");
			getCount.put("Compensatory Off", sup[2] != null ? sup[2].toString() : "");
			getCount.put("Maternity Leave", sup[3] != null ? sup[3].toString() : "");
			getCount.put("Paternity Leave", sup[4] != null ? sup[4].toString() : "");
			getCount.put("Sick Leave", sup[5] != null ? sup[5].toString() : "");
			getCount.put("Unpaid Leave", sup[6] != null ? sup[6].toString() : "");

			employeeLeaveDetails.add(getCount);
		}

		return employeeLeaveDetails;
	}

	@Override
	public List<Map<String, Object>> getLeaveCode(Long orgId) {

		Set<Object[]> getLeaveCode = leaveDetailsRepo.findLeaveCode(orgId);
		return getLeave(getLeaveCode);
	}

	private List<Map<String, Object>> getLeave(Set<Object[]> getPayment) {
		List<Map<String, Object>> employeeLeaveDetails = new ArrayList<>();
		for (Object[] sup : getPayment) {
			Map<String, Object> getCount = new HashMap<>();
			getCount.put("Leave Type", sup[0] != null ? sup[0].toString() : "");
			getCount.put("Leave Code", sup[1] != null ? sup[1].toString() : "");

			employeeLeaveDetails.add(getCount);
		}

		return employeeLeaveDetails;
	}

	@Override
	public Map<String, Object> createUpdateRequestLeave(RequestLeaveDTO requestLeaveDTO) throws ApplicationException {
		RequestLeaveVO requestLeaveVO;
		String message;

		if (ObjectUtils.isEmpty(requestLeaveDTO.getId())) {
			// Create new leave request
			requestLeaveVO = new RequestLeaveVO();
			requestLeaveVO.setCreatedBy(requestLeaveDTO.getCreatedBy());
			requestLeaveVO.setUpdatedBy(requestLeaveDTO.getCreatedBy());
			message = "RequestLeave Creation Successfully";
		} else {
			// Update existing leave request
			requestLeaveVO = requestLeaveRepo.findById(requestLeaveDTO.getId())
					.orElseThrow(() -> new ApplicationException(
							"RequestLeave details not found with ID: " + requestLeaveDTO.getId()));
			requestLeaveVO.setCreatedBy(requestLeaveDTO.getCreatedBy());
			message = "RequestLeave updated successfully";
		}

		// Mapping the fields from DTO to VO
		requestLeaveVO = getRequestLeaveVOFromRequestLeaveDTO(requestLeaveVO, requestLeaveDTO);

		// Handle leave updates based on the employee's current leave balances
		updateEmployeeLeaveDetails(requestLeaveVO);

		// Save the RequestLeaveVO
		requestLeaveRepo.save(requestLeaveVO);

		// Prepare response
		Map<String, Object> response = new HashMap<>();
		response.put("requestLeaveVO", requestLeaveVO);
		response.put("message", message);
		return response;
	}

	private RequestLeaveVO getRequestLeaveVOFromRequestLeaveDTO(RequestLeaveVO requestLeaveVO,
			RequestLeaveDTO requestLeaveDTO) {
		// Mapping fields from RequestLeaveDTO to RequestLeaveVO
		requestLeaveVO.setLeaveType(requestLeaveDTO.getLeaveType());
		requestLeaveVO.setLeaveCode(requestLeaveDTO.getLeaveCode());
		requestLeaveVO.setNotify(requestLeaveDTO.getNotify());
		requestLeaveVO.setFromDate(requestLeaveDTO.getFromDate());
		requestLeaveVO.setToDate(requestLeaveDTO.getToDate());
		requestLeaveVO.setTotalLeave(requestLeaveDTO.getTotalLeave());
		requestLeaveVO.setEmpId(requestLeaveDTO.getEmpId());
		requestLeaveVO.setOrgId(requestLeaveDTO.getOrgId());
		requestLeaveVO.setRemarks(requestLeaveDTO.getRemarks());
		requestLeaveVO.setActive(requestLeaveDTO.isActive());
		requestLeaveVO.setDayType(requestLeaveDTO.getDayType());
		return requestLeaveVO;
	}

	private void updateEmployeeLeaveDetails(RequestLeaveVO requestLeaveVO) throws ApplicationException {
		// Fetch employee's leave details
		Optional<EmployeeLeaveDetailsVO> leaveDetailsVOOpt = employeeLeaveDetailsRepo
				.findByEmployeeId(requestLeaveVO.getEmpId());

		if (leaveDetailsVOOpt.isPresent()) {
			EmployeeLeaveDetailsVO employeeLeaveDetailsVO = leaveDetailsVOOpt.get();
			String leaveCode = requestLeaveVO.getLeaveCode();
			int requestedLeave = requestLeaveVO.getTotalLeave();

			// Map leaveCode to corresponding leave type update logic
			Map<String, Consumer<Integer>> leaveUpdater = Map.of("CL", (count) -> {
				try {
					updateLeave(employeeLeaveDetailsVO::setCl, employeeLeaveDetailsVO.getCl(), count);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, "UL", (count) -> {
				try {
					updateLeave(employeeLeaveDetailsVO::setUl, employeeLeaveDetailsVO.getUl(), count);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, "ML", (count) -> {
				try {
					updateLeave(employeeLeaveDetailsVO::setMl, employeeLeaveDetailsVO.getMl(), count);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, "PL", (count) -> {
				try {
					updateLeave(employeeLeaveDetailsVO::setPl, employeeLeaveDetailsVO.getPl(), count);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, "AL", (count) -> {
				try {
					updateLeave(employeeLeaveDetailsVO::setAl, employeeLeaveDetailsVO.getAl(), count);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, "CO", (count) -> {
				try {
					updateLeave(employeeLeaveDetailsVO::setCo, employeeLeaveDetailsVO.getCo(), count);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}, "SL", (count) -> {
				try {
					updateLeave(employeeLeaveDetailsVO::setSl, employeeLeaveDetailsVO.getSl(), count);
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

			// Process leave if valid
			if (leaveUpdater.containsKey(leaveCode)) {
				leaveUpdater.get(leaveCode).accept(requestedLeave);
				employeeLeaveDetailsRepo.save(employeeLeaveDetailsVO);
			} else {
				throw new ApplicationException("Error: Invalid leave code.");
			}
		} else {
			throw new ApplicationException("Error: Employee leave details not found.");
		}
	}

	// Update the leave count after validation
	private void updateLeave(Consumer<Integer> setLeave, int availableLeave, int requestedLeave)
			throws ApplicationException {
		if (availableLeave >= requestedLeave) {
			setLeave.accept(availableLeave - requestedLeave);
		} else {
			throw new ApplicationException("Error: Not enough leave available.");
		}
	}

	@Override
	public List<RequestLeaveVO> getAllNewLeaveRequest(Long orgId, Long empId) {
		return requestLeaveRepo.findAllRequestLeave(orgId, empId);
	}

	@Override
	public ResponseEntity<List<CheckinVO>> excelUploadForCheckIn(@RequestParam MultipartFile[] files,
			@RequestParam(required = false) String createdBy, @RequestParam(required = false) Long orgId) {
		List<CheckinVO> checkinList = new ArrayList<>();
		try {
			// Iterate over all files (if multiple files are uploaded)
			for (MultipartFile file : files) {
				InputStream inputStream = file.getInputStream();
				Workbook workbook = new XSSFWorkbook(inputStream);
				Sheet sheet = workbook.getSheetAt(0);

				// Iterate over rows starting from row 1 (skipping header)
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					Row row = sheet.getRow(i);
					if (row == null)
						continue;

					// Extract values from the row
					String empcode = row.getCell(1).toString().trim(); // EmpCode (Column 1)
					Date checkinDate = parseDate(row.getCell(0).toString()); // Date (Column 0)
					Date entryTime = parseTime(row.getCell(2).toString()); // Entry Time (Column 2)
					Date exitTime = parseTime(row.getCell(3).toString()); // Exit Time (Column 3)
					String branch = row.getCell(5).toString().trim(); // Branch (Column 5)
					String month = row.getCell(4).toString().trim(); // Month (Column 4)

					// Ensure checkinDate is not null
					if (checkinDate != null) {
						// Create IN record (if entryTime is available)
						if (entryTime != null && !entryTime.equals("--:--")) {
							CheckinVO checkinIn = new CheckinVO();
							checkinIn.setEmpcode(empcode);
							checkinIn.setCheckin_date(checkinDate); // Set checkin_date (same date for both IN and OUT)
							checkinIn.setEntry_time(entryTime); // Setting the IN time as the entry_time
							checkinIn.setStatus("IN"); // Set status to "IN"
							checkinIn.setBranch(branch);
							checkinIn.setOrgId(orgId); // Set orgId if required

							checkinList.add(checkinIn); // Add IN record to the list
						}

						// Create OUT record (if exitTime is available)
						if (exitTime != null && !exitTime.equals("--:--")) {
							CheckinVO checkinOut = new CheckinVO();
							checkinOut.setEmpcode(empcode);
							checkinOut.setCheckin_date(checkinDate); // Set checkin_date (same date for both IN and OUT)
							checkinOut.setEntry_time(exitTime); // Setting the OUT time as the entry_time
							checkinOut.setStatus("OUT"); // Set status to "OUT"
							checkinOut.setBranch(branch);
							checkinOut.setOrgId(orgId); // Set orgId if required

							checkinList.add(checkinOut); // Add OUT record to the list
						}
					}
				}

				workbook.close(); // Close the workbook
			}

		} catch (Exception e) {
			LOGGER.error("Error while processing Excel file for check-in", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

		return ResponseEntity.ok(checkinList); // Return the list of processed CheckinVO objects
	}

	private Date parseDate(String dateStr) throws Exception {
		// Check for empty strings or the default Excel date ("31-Dec-1899")
		if (dateStr == null || dateStr.trim().isEmpty() || dateStr.equals("31-Dec-1899")) {
			return null; // Skip invalid or empty date strings
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			return dateFormat.parse(dateStr); // Try to parse the date
		} catch (ParseException e) {
			// If parsing fails, log and return null
			LOGGER.error("Error parsing date: {}", dateStr, e);
			return null;
		}
	}

	// Method to parse time from string (e.g., "HH:mm")
	private Date parseTime(String timeStr) throws Exception {
		if ("--:--".equals(timeStr)) {
			return null; // No time recorded
		}
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		return timeFormat.parse(timeStr);
	}

	@Override
	public Optional<RequestLeaveVO> getNewLeaveRequestById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public int getTotalRows() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSuccessfulUploads() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSuccessfulUploads1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalRows1() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Map<String, Object>> getAttendanceDetailsOfEmpForMonth(Long orgId, String fromDate,
			String toDate) {
		Set<Object[]> getAttendanceDetails = leaveDetailsRepo.findAttendanceDetails(orgId,fromDate,toDate);
		return getAttendance(getAttendanceDetails);
	}

	private List<Map<String, Object>> getAttendance(Set<Object[]> getPayment) {
		List<Map<String, Object>> employeeLeaveDetails = new ArrayList<>();
		for (Object[] sup : getPayment) {
			Map<String, Object> getCount = new HashMap<>();
			getCount.put("empCode", sup[0] != null ? sup[0].toString() : "");
			getCount.put("empName", sup[1] != null ? sup[1].toString() : "");
			getCount.put("totalDays",  Integer.parseInt(sup[2].toString()));
			getCount.put("consumedLeave",  sup[3] != null ? sup[3].toString() : "");
			getCount.put("precentDays",  sup[4] != null ? sup[4].toString() : "");

			employeeLeaveDetails.add(getCount);
		}

		return employeeLeaveDetails;
	}

}
