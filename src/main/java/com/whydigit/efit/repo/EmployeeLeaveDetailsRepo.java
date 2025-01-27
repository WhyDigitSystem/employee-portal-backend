package com.whydigit.efit.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.EmployeeLeaveDetailsVO;
import com.whydigit.efit.entity.EmployeeVO;

@Repository
public interface EmployeeLeaveDetailsRepo extends JpaRepository<EmployeeLeaveDetailsVO, Long>{

	List<EmployeeLeaveDetailsVO> findByOrgId(Long orgId);

	
	Optional<EmployeeLeaveDetailsVO> findByEmployeeVOAndOrgId(EmployeeVO employeeVO, long orgId);

}
