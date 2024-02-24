package com.whydigit.efit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.whydigit.efit.dto.ResponseDTO;
import com.whydigit.efit.entity.PermissionRequestVO;

public interface PermissionRequestRepo extends JpaRepository<PermissionRequestVO, Integer> {

	List<PermissionRequestVO> findByEmpcode(String empcode);

	@Query("select a from PermissionRequestVO a, EmployeeDetailsVO b where a.empcode=b.empcode and b.reporting_person=(select c.id from EmployeeDetailsVO c where c.orgId=?1 and c.empcode=?2)")
	List<PermissionRequestVO> finAllByapproval(Long orgId, String empcode);

}
