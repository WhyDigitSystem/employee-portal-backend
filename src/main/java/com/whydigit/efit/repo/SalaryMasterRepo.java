package com.whydigit.efit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.whydigit.efit.entity.SalaryMasterVO;

public interface SalaryMasterRepo extends JpaRepository<SalaryMasterVO, Long> {

	@Query(value = "select a from SalaryMasterVO a where a.orgid=?1")
	List<SalaryMasterVO> findAllByActive(Long orgId);

}
