package com.whydigit.efit.repo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.dto.logincreationDTO;
import com.whydigit.efit.entity.EmployeeDetailsVO;

@Repository
public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetailsVO, Long> {

	List<EmployeeDetailsVO> findAllByOrgId(long orgId);

	@Query("select e.id,e.empname from EmployeeDetailsVO e where e.orgId=?1 and e.role=?2")
	Set<Object[]> findAllByRole(long orgId, String role);

	

	

	


	

	


	

}
