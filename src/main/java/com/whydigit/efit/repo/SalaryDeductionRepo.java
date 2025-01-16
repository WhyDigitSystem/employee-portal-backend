package com.whydigit.efit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.SalaryStructureDeductionVO;
import com.whydigit.efit.entity.SalaryStructureVO;

public interface SalaryDeductionRepo extends JpaRepository<SalaryStructureDeductionVO, Long> {

	List<SalaryStructureDeductionVO> findBySalaryStructureVO(SalaryStructureVO salaryStructureVO);

}
