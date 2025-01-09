package com.whydigit.efit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.whydigit.efit.entity.EarningsVO;

@Service
public interface EarningDeductionService {

	public List<EarningsVO> getAllEarnings();

	public EarningsVO getAllEarningsById(Long id);

	public EarningsVO createEarnings(EarningsVO earningsVO);

	public EarningsVO updateEarnings(EarningsVO earningsVO, Long id);

}
