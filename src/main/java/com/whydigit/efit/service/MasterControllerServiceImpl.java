package com.whydigit.efit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.EmployeeDTO;
import com.whydigit.efit.dto.LeaveDetailsDTO;
import com.whydigit.efit.dto.Role;
import com.whydigit.efit.entity.EmployeeLeaveDetailsVO;
import com.whydigit.efit.entity.EmployeeVO;
import com.whydigit.efit.entity.LeaveDetailsVO;
import com.whydigit.efit.entity.UserVO;
import com.whydigit.efit.exception.ApplicationException;
import com.whydigit.efit.repo.EmployeeLeaveDetailsRepo;
import com.whydigit.efit.repo.EmployeeRepo;
import com.whydigit.efit.repo.LeaveDetailsRepo;
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
	        employeeVO.setUpdatedby(employeeDTO.getCreatedBy());
	        message = "Employee created successfully";
	    } else {
	        // Fetch existing employee for update
	        employeeVO = employeeRepo.findById(employeeDTO.getId())
	                .orElseThrow(() -> new ApplicationException("Employee details not found with ID: " + employeeDTO.getId()));

	        // Validate uniqueness only if the fields are being updated
	        validateAndUpdateEmployeeFields(employeeVO, employeeDTO);
	        message = "Employee updated successfully";
	    }

	    // Map DTO to VO
	    employeeVO = getEmployeeVOFromEmployeeDTO(employeeVO, employeeDTO);

	    // Save employee
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
	        throw new ApplicationException(String.format("This EmpCode '%s' already exists in this organization", employeeDTO.getEmpCode()));
	    }
//	    if (employeeRepo.existsByEmpNameAndOrgId(employeeDTO.getEmpName(), employeeDTO.getOrgId())) {
//	        throw new ApplicationException(String.format("This EmpName '%s' already exists in this organization", employeeDTO.getEmpName()));
//	    }
	    if (employeeRepo.existsByEmailAndOrgId(employeeDTO.getEmail(), employeeDTO.getOrgId())) {
	        throw new ApplicationException(String.format("This Email '%s' already exists in this organization", employeeDTO.getEmail()));
	    }
	    if (employeeRepo.existsByMobileNoAndOrgId(employeeDTO.getMobileNo(), employeeDTO.getOrgId())) {
	        throw new ApplicationException(String.format("This MobileNo '%s' already exists in this organization", employeeDTO.getMobileNo()));
	    }
	    if (employeeRepo.existsByAadharAndOrgId(employeeDTO.getAadhar(), employeeDTO.getOrgId())) {
	        throw new ApplicationException(String.format("This Aadhar '%s' already exists in this organization", employeeDTO.getAadhar()));
	    }
	    if (employeeRepo.existsByAccountNoAndOrgId(employeeDTO.getAccountNo(), employeeDTO.getOrgId())) {
	        throw new ApplicationException(String.format("This AccountNo '%s' already exists in this organization", employeeDTO.getAccountNo()));
	    }
	}

	// Validate and update fields for existing employees
	private void validateAndUpdateEmployeeFields(EmployeeVO employeeVO, EmployeeDTO employeeDTO) throws ApplicationException {
	    if (!employeeVO.getEmpCode().equalsIgnoreCase(employeeDTO.getEmpCode())) {
	        if (employeeRepo.existsByEmpCodeAndOrgId(employeeDTO.getEmpCode(), employeeDTO.getOrgId())) {
	            throw new ApplicationException(String.format("This EmpCode '%s' already exists in this organization", employeeDTO.getEmpCode()));
	        }
	        employeeVO.setEmpCode(employeeDTO.getEmpCode());
	    }
//	    if (!employeeVO.getEmpName().equalsIgnoreCase(employeeDTO.getEmpName())) {
//	        if (employeeRepo.existsByEmpNameAndOrgId(employeeDTO.getEmpName(), employeeDTO.getOrgId())) {
//	            throw new ApplicationException(String.format("This EmpName '%s' already exists in this organization", employeeDTO.getEmpName()));
//	        }
//	        employeeVO.setEmpName(employeeDTO.getEmpName());
//	    }
	    if (!employeeVO.getEmail().equalsIgnoreCase(employeeDTO.getEmail())) {
	        if (employeeRepo.existsByEmailAndOrgId(employeeDTO.getEmail(), employeeDTO.getOrgId())) {
	            throw new ApplicationException(String.format("This Email '%s' already exists in this organization", employeeDTO.getEmail()));
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
	    employeeVO.setRole(employeeDTO.getAadhar());
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
	    UserVO userVO = new UserVO();
	    userVO.setActive(employeeDTO.isActive());
	    userVO.setEmail(employeeDTO.getEmail());
	    userVO.setEmpcode(employeeDTO.getEmpCode());
	    userVO.setEmpname(employeeDTO.getEmpName());
	    userVO.setEmpId(employeeVO.getId());
	    userVO.setOrgId(employeeDTO.getOrgId());
	    userVO.setRole(Role.USER);
	    userVO.setPassword("$10$NWMg1GT3w0KnuZowQcUObOmJoivV.abut6ZcJ0u3DdaFYYKMUgeA2");
	    userRepo.save(userVO);
	}

	// Create or update employee leave details
	private void createOrUpdateEmployeeLeaveDetails(EmployeeVO employeeVO, EmployeeDTO employeeDTO) throws ApplicationException {
	    EmployeeLeaveDetailsVO employeeLeaveDetailsVO = employeeLeaveDetailsRepo
	            .findByEmployeeVOAndOrgId(employeeVO, employeeVO.getOrgId())
	            .orElseGet(() -> {
	                EmployeeLeaveDetailsVO newDetails = new EmployeeLeaveDetailsVO();
	                newDetails.setEmployeeVO(employeeVO);
	                newDetails.setOrgId(employeeVO.getOrgId());
	                newDetails.setGender(employeeVO.getGender());
	                return newDetails;
	            });

	    List<LeaveDetailsVO> leaveDetailsList = leaveDetailsRepo.findByOrgId(employeeDTO.getOrgId());
	    if (leaveDetailsList == null || leaveDetailsList.isEmpty()) {
	        throw new ApplicationException("No leave details found for Org ID: " + employeeDTO.getOrgId());
	    }

	    for (LeaveDetailsVO leaveDetailsVO : leaveDetailsList) {
	    	
	    	 if (!"all".equalsIgnoreCase(leaveDetailsVO.getLeaveApplicable()) 
	    	            && !leaveDetailsVO.getLeaveApplicable().equalsIgnoreCase(employeeVO.getGender())) {
	    	            continue; // Skip this leave if not applicable
	    	        }
	    	
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
	                System.out.println("Unknown leave type: " + leaveDetailsVO.getLeaveType());
	        }
	    }
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

}
