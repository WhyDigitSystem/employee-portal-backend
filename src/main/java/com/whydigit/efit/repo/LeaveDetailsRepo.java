package com.whydigit.efit.repo;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;

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

	@Query(value="select t.empcode,t.empname,u.total_days totaldays,t.totalleavedays consumedleaves,(u.total_days-t.totalleavedays) presentdays from\r\n"
			+ "(select a.empcode,count(a.empcode)totalworkingdays from\r\n"
			+ "(select date(checkin_date),empcode from checkin  where org_id=?1 and date(checkin_date) between ?2 and ?3 \r\n"
			+ "group by empcode,date(checkin_date)) a group by a.empcode order by a.empcode asc)s,\r\n"
			+ "(SELECT DATEDIFF(end_date, start_date) + 1 AS total_days\r\n"
			+ "FROM (SELECT ?2 AS start_date, ?3 AS end_date) AS date_range)u,\r\n"
			+ "(SELECT  empcode,empname,sum(totaldays)totalleavedays FROM leaverequest where\r\n"
			+ " date(fromdate) between ?2 and ?3 and date(todate) between ?2 and ?3\r\n"
			+ "group by empcode,empname)t where s.empcode=t.empcode",nativeQuery =true)
	Set<Object[]> findAttendanceDetails(Long orgId);



}
