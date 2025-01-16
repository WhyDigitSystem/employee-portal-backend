package com.whydigit.efit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whydigit.efit.entity.SalaryMasterVO;
import com.whydigit.efit.repo.SalaryMasterRepo;

@Service
public class SalaryMasterServiceImpl implements SalaryMasterService {

	@Autowired
	SalaryMasterRepo salaryMasterRepo;

	@Override
	public List<SalaryMasterVO> getAllSalaryMaster() {
		return salaryMasterRepo.findAll();
	}

	@Override
	public SalaryMasterVO getAllSalaryMasterById(Long id) {
		return salaryMasterRepo.findById(id).orElse(null);
	}

	@Override
	public SalaryMasterVO createSalaryMaster(SalaryMasterVO salaryMasterVO) {
		return salaryMasterRepo.save(salaryMasterVO);
	}

	@Override
	public SalaryMasterVO updateSalaryMaster(SalaryMasterVO salaryMasterVO, Long id) {
		// Fetch the existing salaryMaster record from the database
		SalaryMasterVO existingSalaryMaster = salaryMasterRepo.findById(id).orElse(null);

		// Update fields in the existing record
		existingSalaryMaster.setHeadings(salaryMasterVO.getHeadings());
		existingSalaryMaster.setCode(salaryMasterVO.getCode());
		existingSalaryMaster.setCategory(salaryMasterVO.getCategory());
		existingSalaryMaster.setType(salaryMasterVO.getType());
		existingSalaryMaster.setAmount(salaryMasterVO.getAmount());
		existingSalaryMaster.setActive(salaryMasterVO.isActive());
		existingSalaryMaster.setOrgid(salaryMasterVO.getOrgid());

		return salaryMasterRepo.save(existingSalaryMaster);
	}
}

