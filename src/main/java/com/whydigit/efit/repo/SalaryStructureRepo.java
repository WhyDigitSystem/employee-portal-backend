package com.whydigit.efit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.SalaryStructureVO;

public interface SalaryStructureRepo extends JpaRepository<SalaryStructureVO, Long> {

	List<SalaryStructureVO> findByOrgId(Long orgId);

	SalaryStructureVO findByOrgIdAndEmployeeCodeAndMonthAndYear(Long orgId, String empCode, String month, String year);

}
