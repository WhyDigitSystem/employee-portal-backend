package com.whydigit.efit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.whydigit.efit.entity.SalaryMasterVO;

@Service
public interface SalaryMasterService {

	public List<SalaryMasterVO> getAllSalaryMaster();

	public SalaryMasterVO getAllSalaryMasterById(Long id);

	public SalaryMasterVO createSalaryMaster(SalaryMasterVO earningsVO);

	public SalaryMasterVO updateSalaryMaster(SalaryMasterVO earningsVO, Long id);

}
