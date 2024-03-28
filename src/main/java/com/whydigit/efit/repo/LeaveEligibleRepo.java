package com.whydigit.efit.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.LeaveEligibleVO;

public interface LeaveEligibleRepo extends JpaRepository<LeaveEligibleVO, Long>{

//	@Query(value = "SELECT ")
	Set<Object[]> findAllLeaveEligibleByOrgId(long orgId);

	Set<Object[]> findAllLeaveEligibleByOrgIdAndEmpcodeAndBranchid(long orgId, String empcode, long branchid);

}
