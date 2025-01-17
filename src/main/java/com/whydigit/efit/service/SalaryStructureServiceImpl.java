package com.whydigit.efit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whydigit.efit.dto.SalaryStructureDTO;
import com.whydigit.efit.entity.EmployeeDetailsVO;
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
	public List<EmployeeDetailsVO> getEmployeeNameDetails(Long orgId) {
		return employeeDetailsRepo.getByOrgIdAndActiveEmployees(orgId);
	}

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
		salaryStructureVO.setOrgId(salaryStructureDTO.getOrgId());
		salaryStructureVO.setBankAccountNo(salaryStructureDTO.getBankAccountNo());
		salaryStructureVO.setPosition(salaryStructureDTO.getPosition());
		salaryStructureVO.setDateOfJoining(salaryStructureDTO.getDateOfJoining());
		
		if (salaryStructureDTO.getId() != null) {
			List<SalaryStructureEarningsVO> detailsVOs = salaryEarningsRepo.findBySalaryStructureVO(salaryStructureVO);
			salaryEarningsRepo.deleteAll(detailsVOs);
		}

// Map earnings list
		if (!ObjectUtils.isEmpty(salaryStructureDTO.getSalaryStructureEarningsDTO())) {
			List<SalaryStructureEarningsVO> earningsVOList = salaryStructureDTO.getSalaryStructureEarningsDTO().stream()
					.map(earningDTO -> {
						SalaryStructureEarningsVO earningVO = new SalaryStructureEarningsVO();
						earningVO.setHeading(earningDTO.getHeading());
						earningVO.setAmount(earningDTO.getAmount());
						earningVO.setSalaryStructureVO(salaryStructureVO);
						return earningVO;
					}).collect(Collectors.toList());
			salaryStructureVO.setSalaryStructureEarningsVO(earningsVOList);
		}

		if (salaryStructureDTO.getId() != null) {
			List<SalaryStructureDeductionVO> detailsVOs = salaryDeductionRepo.findBySalaryStructureVO(salaryStructureVO);
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
						return deductionVO;
					}).collect(Collectors.toList());
			salaryStructureVO.setSalaryStructureDeductionVO(deductionVOList);
		}
	}

	@Override
	public List<SalaryMasterVO> getAllSalaryMasterForSalaryStructure(Long orgId) {
		
		List<SalaryMasterVO> master= salaryMasterRepo.findAllByActive(orgId);
		
		return master.stream().filter(mp -> mp.isActive()).toList();
	}

	@Override
	public List<SalaryStructureVO> getAllSalaryStructures(Long orgId) {
		
		
		return salaryStructureRepo.findByOrgId(orgId);
	}

	@Override
	public SalaryStructureVO getSalaryStructureById(Long id) {
		
		SalaryStructureVO salaryStructureVO= salaryStructureRepo.findById(id).get();
		 return salaryStructureVO;
	}
}
