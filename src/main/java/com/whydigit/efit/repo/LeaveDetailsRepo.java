package com.whydigit.efit.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.LeaveDetailsVO;

@Repository
public interface LeaveDetailsRepo extends JpaRepository<LeaveDetailsVO, Long> {

	boolean existsByLeaveTypeAndOrgId(String leaveType, Long orgId);

	boolean existsByLeaveCodeAndOrgId(String leaveType, Long orgId);

	@Query(nativeQuery = true, value = "select * from leavedetails where orgid=?1")
	List<LeaveDetailsVO> getAllLeaveDetailsByOrgId(Long orgId);

	List<LeaveDetailsVO> findByOrgId(long orgId);

	@Query(value ="select l.leavetype,l.leavecode from leavedetails l where orgid=?1 and active=1 group by l.leavetype,l.leavecode",nativeQuery =true)
	Set<Object[]> findLeaveCode(Long orgId);



}
