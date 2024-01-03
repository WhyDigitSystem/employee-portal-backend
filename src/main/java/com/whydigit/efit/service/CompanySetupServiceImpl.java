package com.whydigit.efit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whydigit.efit.entity.BranchVO;
import com.whydigit.efit.entity.CompanySetVO;
import com.whydigit.efit.entity.CompanySetupVO;
import com.whydigit.efit.repo.BranchRepo;
import com.whydigit.efit.repo.CompanySetRepo;
import com.whydigit.efit.repo.CompanySetupRepo;


@Service
public class CompanySetupServiceImpl implements CompanySetupService {

	@Autowired
	private CompanySetupRepo companySetupRepo;
	
	@Autowired
	private BranchRepo branchRepo;
	
	@Autowired
	private CompanySetRepo companySetRepo;

	@Override
	
	public List<CompanySetupVO> getAllcompanies() {
		return companySetupRepo.findAll();
	}

	@Override
	public Optional<CompanySetupVO> getCompanyById(int id) {
		return companySetupRepo.findById(id);
	}

	@Override
	public CompanySetupVO createCompany(CompanySetupVO companySetupVO) {
		return companySetupRepo.save(companySetupVO);
	}

	@Override
	public Optional<CompanySetupVO> updateCompany(CompanySetupVO companySetupVO) {
		if (companySetupRepo.existsById(companySetupVO.getId())) {
			return Optional.of(companySetupRepo.save(companySetupVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteCompany(int id) {
		companySetupRepo.deleteById(id);
	}
	
	@Override	
	public List<BranchVO> getAllBranch() {
		return branchRepo.findAll();
	}

	@Override
	public Optional<BranchVO> getBranchById(int id) {
		return branchRepo.findById(id);
	}

	@Override
	public BranchVO createBranch(BranchVO branchVO) {
		return branchRepo.save(branchVO);
	}

	@Override
	public Optional<BranchVO> updateBranch(BranchVO branchVO) {
		if (branchRepo.existsById(branchVO.getId())) {
			return Optional.of(branchRepo.save(branchVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteBranch(int id) {
		branchRepo.deleteById(id);
	}
	
	@Override	
	public List<CompanySetVO> getAllCompanySet() {
		return companySetRepo.findAll();
	}

	@Override
	public Optional<CompanySetVO> getCompanySetById(int id) {
		return companySetRepo.findById(id);
	}

	@Override
	public CompanySetVO createCompanySet(CompanySetVO companySetVO) {
		return companySetRepo.save(companySetVO);
	}

	@Override
	public Optional<CompanySetVO> updateCompanySet(CompanySetVO companySetVO) {
		if (companySetRepo.existsById(companySetVO.getId())) {
			return Optional.of(companySetRepo.save(companySetVO));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void deleteCompanySet(int id) {
		companySetRepo.deleteById(id);
	}


}
