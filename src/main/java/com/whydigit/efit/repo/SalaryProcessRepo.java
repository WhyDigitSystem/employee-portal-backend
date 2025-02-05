package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.SalaryProcessVO;

public interface SalaryProcessRepo extends JpaRepository<SalaryProcessVO, Long> {


	SalaryProcessVO findByOrgIdAndEmployeeCodeAndMonthAndYear(Long orgId, String empCode, String month, String year);

}
