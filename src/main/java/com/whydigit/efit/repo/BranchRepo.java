package com.whydigit.efit.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.whydigit.efit.entity.BranchVO;

public interface BranchRepo extends JpaRepository<BranchVO, Long> {
	@Query(value = "SELECT * FROM branch WHERE org_id = ?1", nativeQuery = true)
	List<BranchVO> getBranchByOrgId(Long orgId);

	@Query(nativeQuery = true,value="select e.id,e.branch_code,e.branch_name from branch e where e.org_id=?1")
	Set<Object[]> findBranchCodeAndBranchByOrgId(Long orgId);

}
