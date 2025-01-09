package com.whydigit.efit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whydigit.efit.entity.DeductionsVO;
import com.whydigit.efit.entity.EarningsVO;
import com.whydigit.efit.repo.EarningsRepo;

@Service
public class EarningDeductionServiceImpl implements EarningDeductionService {

	@Autowired
	EarningsRepo earningsRepo;

	@Override
	public List<EarningsVO> getAllEarnings() {
		return earningsRepo.findAll();
	}

	@Override
	public EarningsVO getAllEarningsById(Long id) {
		return earningsRepo.findById(id).orElse(null);
	}

	@Override
	public EarningsVO createEarnings(EarningsVO earningsVO) {
		if (earningsVO.getDeductionsVO() != null) {
			earningsVO.getDeductionsVO().setEarningsVO(earningsVO);
		}
		return earningsRepo.save(earningsVO);
	}

	@Override
	public EarningsVO updateEarnings(EarningsVO earningsVO, Long id) {
		// Fetch the existing earnings record from the database
		EarningsVO existingEarnings = earningsRepo.findById(id).orElse(null);

		// Update fields in the existing record
		existingEarnings.setBasicHRA(earningsVO.getBasicHRA());
		existingEarnings.setBonus(earningsVO.getBonus());
		existingEarnings.setIncentives(earningsVO.getIncentives());
		existingEarnings.setProjectAllowance(earningsVO.getProjectAllowance());
		existingEarnings.setSpecialAllowance(earningsVO.getSpecialAllowance());

		// Update deductions if present
		if (earningsVO.getDeductionsVO() != null) {
			DeductionsVO deductions = earningsVO.getDeductionsVO();

			if (existingEarnings.getDeductionsVO() == null) {
				// If deductions do not exist, create a new one
				deductions.setEarningsVO(existingEarnings);
				existingEarnings.setDeductionsVO(deductions);
			} else {
				// Update the existing deductions
				DeductionsVO existingDeductions = existingEarnings.getDeductionsVO();
				existingDeductions.setProfessionalTax(deductions.getProfessionalTax());
				existingDeductions.setPf(deductions.getPf());
				existingDeductions.setVpf(deductions.getVpf());
				existingDeductions.setMedicalclaim(deductions.getMedicalclaim());
			}
		}

		return earningsRepo.save(existingEarnings);
	}
}
