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
	
	
	@Query(nativeQuery = true,value = "select a.Empcode,b.Empname,a.Sundays,a.Holidays,a.TotalDays,a.OfficeWorkingDays,a.TotalLeaveCount,CLLeavecount,PLLeavecount,PTLLeavecount,MLLeavecount,a.PresentDays,\r\n"
			+ "1 CLCredit, PresentDays+1 EmpWorkingDays,OfficeWorkingDays-TotalLeaveCount SalaryDays from(\r\n"
			+ "SELECT \r\n"
			+ "    C.Empcode, \r\n"
			+ "    C.Presentdays, \r\n"
			+ "    case when LV.leavecount is null then '-' else  LV.leavecount end TotalLeavecount,\r\n"
			+ "    case when LV.CLleave is null then '-' else  LV.CLleave end CLLeavecount,\r\n"
			+ "    case when LV.PLleave is null then '-' else  LV.PLleave end PLLeavecount,\r\n"
			+ "    case when LV.PTLleave is null then '-' else  LV.PTLleave end PTLLeavecount,\r\n"
			+ "    case when LV.MLleave is null then '-' else  LV.MLleave end MLLeavecount,\r\n"
			+ "    S.Sundays,\r\n"
			+ "    H.Holidays,\r\n"
			+ "    T.TotalDays,\r\n"
			+ "    (T.totalDays-(S.Sundays+H.Holidays))OfficeWorkingDays\r\n"
			+ "FROM \r\n"
			+ "    (SELECT empcode, SUM(noofdays) AS presentdays FROM checkincount WHERE chkdate BETWEEN ?1 AND ?2 GROUP BY empcode) AS C\r\n"
			+ "LEFT JOIN \r\n"
			+ "    (SELECT empcode, SUM(leavecount) AS leavecount,\r\n"
			+ "    case when leavetype='CL' then Sum(leavecount) else 0 end CLleave,\r\n"
			+ "    case when leavetype='PL' then Sum(leavecount) else 0 end PLleave,\r\n"
			+ "    case when leavetype='PTL' then Sum(leavecount) else 0 end PTLleave,\r\n"
			+ "    case when leavetype='ML' then Sum(leavecount) else 0 end MLleave\r\n"
			+ "    FROM LeaveView WHERE leavedate BETWEEN ?1 AND ?2 GROUP BY empcode,leavetype) AS LV\r\n"
			+ "ON \r\n"
			+ "    C.empcode = LV.empcode\r\n"
			+ "CROSS JOIN\r\n"
			+ "    (SELECT COUNT(*) AS Sundays FROM employeeportal.calendar WHERE cDayName = 'Sunday' AND cdate BETWEEN ?1 AND ?2) AS S\r\n"
			+ "CROSS JOIN\r\n"
			+ "    (SELECT COUNT(*) AS Holidays FROM employeeportal.holidays WHERE holiday_date BETWEEN ?1 AND ?2) AS H\r\n"
			+ "CROSS JOIN\r\n"
			+ "    (SELECT COUNT(*) AS totalDays FROM employeeportal.calendar WHERE cdate BETWEEN ?1 AND ?2) AS T)a,users b ,branch c where c.id=b.branch_id and c.branch_name=?3 and a.empcode=b.empcode and b.is_active='1' ")
	Set<Object[]> findAllAttendanceByFromAndToDate(String fromdt, String todt,String branch);

	

	

	


	

	


	

}
