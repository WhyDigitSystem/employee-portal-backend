package com.whydigit.efit.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.LeaveTypeVO;

@Repository
public interface LeaveTypeRepo extends JpaRepository<LeaveTypeVO, Integer> {

	@Query(nativeQuery = true,value="select e.leave_type,e.leave_code from leave_type e where e.applicable='ALL'\r\n"
			+ " union\r\n"
			+ "select e.leave_type,e.leave_code from leave_type e where e.applicable=(select b.gender from employee_details b where b.org_id=?1 and b.id=?2)")
	Set<Object[]> findAllType(Long orgId, Long id);

	@Query(value = "SELECT l.leave_type FROM LeaveTypeVO l WHERE orgId=?1")
	Set<Object[]> findLeaveTypeNameByOrgId(long orgId);

	

}
