package com.whydigit.efit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.whydigit.efit.entity.SalaryMasterVO;

public interface SalaryMasterRepo extends JpaRepository<SalaryMasterVO, Long> {

	@Query(value = "select * from salarymaster where orgid=?1",nativeQuery = true)
	List<SalaryMasterVO> findAllByActive(Long orgId);

}
