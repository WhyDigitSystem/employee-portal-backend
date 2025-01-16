package com.whydigit.efit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.SalaryStructureEarningsVO;
import com.whydigit.efit.entity.SalaryStructureVO;

public interface SalaryEarningsRepo extends JpaRepository<SalaryStructureEarningsVO, Long> {

	List<SalaryStructureEarningsVO> findBySalaryStructureVO(SalaryStructureVO salaryStructureVO);

}
