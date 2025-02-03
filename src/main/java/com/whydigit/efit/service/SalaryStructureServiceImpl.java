package com.whydigit.efit.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.SalaryStructureDTO;
import com.whydigit.efit.dto.SalaryStructureDeductionDTO;
import com.whydigit.efit.dto.SalaryStructureEarningsDTO;
import com.whydigit.efit.entity.SalaryMasterVO;
import com.whydigit.efit.entity.SalaryStructureDeductionVO;
import com.whydigit.efit.entity.SalaryStructureEarningsVO;
import com.whydigit.efit.entity.SalaryStructureVO;
import com.whydigit.efit.repo.EmployeeDetailsRepo;
import com.whydigit.efit.repo.SalaryDeductionRepo;
import com.whydigit.efit.repo.SalaryEarningsRepo;
import com.whydigit.efit.repo.SalaryMasterRepo;
import com.whydigit.efit.repo.SalaryStructureRepo;

@Service
public class SalaryStructureServiceImpl implements SalaryStructureService {

	public static final Logger LOGGER = LoggerFactory.getLogger(SalaryStructureServiceImpl.class);

	@Autowired
	SalaryStructureRepo salaryStructureRepo;
	
	@Autowired
	AmountInWordsConverterService amountInWordsConverterService;

	@Autowired
	EmployeeDetailsRepo employeeDetailsRepo;

	@Autowired
	SalaryEarningsRepo salaryEarningsRepo;

	@Autowired
	SalaryMasterRepo salaryMasterRepo;

	@Autowired
	SalaryDeductionRepo salaryDeductionRepo;

//	@Override
//	public List<Map<String, Object>> getEmployeeNameDetails(Long orgId) {
//		Set<Object[]>details=employeeDetailsRepo.getEmployeeName(orgId);
//		return det(details);
//	}
//
//	private List<Map<String, Object>> det(Set<Object[]> details) {
//		List<Map<String,Object>>empdetails= new  ArrayList<>();
//		for(Object[] emp:details)
//		{
//			Map<String, Object> mpDetails= new HashMap<>();
//			mpDetails.put("empName", emp[0].toString());
//			empdetails.add(mpDetails);
//		}
//		return empdetails;
//	}

//	@Override
//	public List<Map<String, Object>> getEmployeeNameDetails(Long orgId) {
//	    Set<Object[]> details = employeeDetailsRepo.getEmployeeName(orgId);
//	    return details.stream()
//	                  .map(emp -> {
//	                      Map<String, Object> mpDetails = new HashMap<>();
//	                      mpDetails.put("empName", emp[0].toString());
//	                      return mpDetails;
//	                  })
//	                  .collect(Collectors.toList());
//	}

	

	@Override
	public Map<String, Object> createUpdateSalaryStructure(SalaryStructureDTO salaryStructureDTO) {
		String message = null;
		SalaryStructureVO salaryStructureVO = new SalaryStructureVO();
		if (ObjectUtils.isEmpty(salaryStructureDTO.getId())) {

			salaryStructureVO.setCreatedBy(salaryStructureDTO.getCreatedBy());
			salaryStructureVO.setModifiedBy(salaryStructureDTO.getCreatedBy());

			message = "Salary Creation Successfully";
		} else {
			salaryStructureVO = salaryStructureRepo.findById(salaryStructureDTO.getId()).get();
			salaryStructureVO.setModifiedBy(salaryStructureDTO.getCreatedBy());
			message = "BuyerOrder Updation Successfully";
		}

		getSalaryStructureVOfromSalaryStructureDTO(salaryStructureVO, salaryStructureDTO);
		salaryStructureRepo.save(salaryStructureVO);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", message);
		response.put("salaryStructureVO", salaryStructureVO);
		return response;

	}

	private void getSalaryStructureVOfromSalaryStructureDTO(SalaryStructureVO salaryStructureVO,
			SalaryStructureDTO salaryStructureDTO) {
// Map basic fields
		salaryStructureVO.setEmployeeCode(salaryStructureDTO.getEmployeeCode());
		salaryStructureVO.setEmployeeName(salaryStructureDTO.getEmployeeName());
		salaryStructureVO.setDateOfBirth(salaryStructureDTO.getDateOfBirth());
		salaryStructureVO.setGrade(salaryStructureDTO.getGrade());
		salaryStructureVO.setDepartment(salaryStructureDTO.getDepartment());
		salaryStructureVO.setPan(salaryStructureDTO.getPan());
		salaryStructureVO.setYear(salaryStructureDTO.getYear());
		salaryStructureVO.setMonth(salaryStructureDTO.getMonth());
		salaryStructureVO.setOrgId(salaryStructureDTO.getOrgId());
		salaryStructureVO.setBankAccountNo(salaryStructureDTO.getBankAccountNo());
		salaryStructureVO.setPosition(salaryStructureDTO.getPosition());
		salaryStructureVO.setDateOfJoining(salaryStructureDTO.getDateOfJoining());

// Delete previous earnings records if updating
		if (salaryStructureDTO.getId() != null) {
			List<SalaryStructureEarningsVO> detailsVOs = salaryEarningsRepo.findBySalaryStructureVO(salaryStructureVO);
			salaryEarningsRepo.deleteAll(detailsVOs);
		}

// Initialize BigDecimal values for earnings and deductions
		BigDecimal totalEarnings = BigDecimal.ZERO;
		BigDecimal totalDeductions = BigDecimal.ZERO;

// Map earnings list
		if (!ObjectUtils.isEmpty(salaryStructureDTO.getSalaryStructureEarningsDTO())) {
			List<SalaryStructureEarningsVO> earningsVOList = salaryStructureDTO.getSalaryStructureEarningsDTO().stream()
					.map(earningDTO -> {
						SalaryStructureEarningsVO earningVO = new SalaryStructureEarningsVO();
						earningVO.setHeading(earningDTO.getHeading());
						earningVO.setAmount(earningDTO.getAmount());
						earningVO.setSalaryStructureVO(salaryStructureVO);

// Accumulate total earnings
						return earningVO;
					}).collect(Collectors.toList());

			totalEarnings = salaryStructureDTO.getSalaryStructureEarningsDTO().stream()
					.map(SalaryStructureEarningsDTO::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

			salaryStructureVO.setSalaryStructureEarningsVO(earningsVOList);
		}

// Delete previous deduction records if updating
		if (salaryStructureDTO.getId() != null) {
			List<SalaryStructureDeductionVO> detailsVOs = salaryDeductionRepo
					.findBySalaryStructureVO(salaryStructureVO);
			salaryDeductionRepo.deleteAll(detailsVOs);
		}

// Map deductions list
		if (!ObjectUtils.isEmpty(salaryStructureDTO.getSalaryStructureDeductionDTO())) {
			List<SalaryStructureDeductionVO> deductionVOList = salaryStructureDTO.getSalaryStructureDeductionDTO()
					.stream().map(deductionDTO -> {
						SalaryStructureDeductionVO deductionVO = new SalaryStructureDeductionVO();
						deductionVO.setHeading(deductionDTO.getHeading());
						deductionVO.setAmount(deductionDTO.getAmount());
						deductionVO.setSalaryStructureVO(salaryStructureVO);

// Accumulate total deductions
						return deductionVO;
					}).collect(Collectors.toList());

			totalDeductions = salaryStructureDTO.getSalaryStructureDeductionDTO().stream()
					.map(SalaryStructureDeductionDTO::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

			salaryStructureVO.setSalaryStructureDeductionVO(deductionVOList);
		}

// Calculate Net Pay (Total Earnings - Total Deductions)
		BigDecimal netPay = totalEarnings.subtract(totalDeductions);

// Set totals in VO
		salaryStructureVO.setTotalEarnings(totalEarnings);
		salaryStructureVO.setTotalDeduction(totalDeductions);
		salaryStructureVO.setNetPay(netPay);
		salaryStructureVO.setAmountInWords(amountInWordsConverterService.convert(netPay.longValue()));
		
	}

	@Override
	public List<SalaryMasterVO> getAllSalaryMasterForSalaryStructure(Long orgId) {

		List<SalaryMasterVO> master = salaryMasterRepo.findAllByActive(orgId);

		return master.stream().filter(mp -> mp.isActive()).toList();
	}

	@Override
	public List<SalaryStructureVO> getAllSalaryStructures(Long orgId) {

		return salaryStructureRepo.findByOrgId(orgId);
	}

	@Override
	public SalaryStructureVO getSalaryStructureById(Long id) {

		SalaryStructureVO salaryStructureVO = salaryStructureRepo.findById(id).get();
		return salaryStructureVO;
	}

	@Override
	public List<Map<String, Object>> getEmployeeNameDetails(Long orgId, String month, String year) {

		Set<Object>empDetails=employeeDetailsRepo.getemployeeDetailsForSalaryStructure(orgId,month,year);
		 return mapEmployeeDetails(empDetails);
	}

	// Convert Set<Object> to List<Map<String, Object>>
	private List<Map<String, Object>> mapEmployeeDetails(Set<Object> empDetails) {
	    List<Map<String, Object>> employeeList = new ArrayList<>();

	    for (Object obj : empDetails) {
	        Object[] row = (Object[]) obj;  // Convert Object to Object[]
	        
	        Map<String, Object> employeeMap = new HashMap<>();
	        employeeMap.put("orgId", row[0]);
	        employeeMap.put("empCode", row[1] != null ? row[1].toString() : ""); 
	        employeeMap.put("empName", row[2] != null ? row[2].toString() : ""); 
	        employeeMap.put("totalDays", row[3] != null ? row[3].toString() : "0"); 
	        employeeMap.put("lop", row[4] != null ? row[4].toString() : "0"); 
	        employeeMap.put("designation", row[5] != null ? row[5].toString() : ""); 
	        employeeMap.put("department", row[6] != null ? row[6].toString() : ""); 
	        employeeMap.put("accountNo", row[7] != null ? row[7].toString() : "0"); 
	        employeeMap.put("branch", row[8] != null ? row[8].toString() : ""); 
	        employeeMap.put("pan", row[9] != null ? row[9].toString() : ""); 
	        employeeMap.put("id", row[10]);
	        employeeMap.put("DOB", row[11] != null ? row[11].toString() : "");
	        employeeMap.put("joiningDate", row[12] != null ? row[12].toString() : "");
	        employeeMap.put("grade", row[13] != null ? row[13].toString() : "");
	        employeeMap.put("uan", row[14] != null ? row[14].toString() : "");

	        employeeList.add(employeeMap);
	    }
	    return employeeList;
	}

	@Override
	public SalaryStructureVO getEmployeeSalaryPDF(Long orgId, String empCode, String month, String year) {
		
		return salaryStructureRepo.findByOrgIdAndEmployeeCodeAndMonthAndYear(orgId,empCode,month,year);
	}
}
