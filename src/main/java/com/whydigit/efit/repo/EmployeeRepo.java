package com.whydigit.efit.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.EmployeeVO;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeVO, Long> {

	boolean existsByEmpCodeAndOrgId(String empCode, long orgId);

	//boolean existsByEmpNameAndOrgId(String empName, long orgId);

	boolean existsByEmailAndOrgId(String email, long orgId);

	boolean existsByMobileNoAndOrgId(String mobileNo, long orgId);

	boolean existsByAadharAndOrgId(String aadhar, long orgId);

	boolean existsByAccountNoAndOrgId(long accountNo, long orgId);

	@Query(nativeQuery = true, value = "select * from employee where orgid=?1 and active=1")
	List<EmployeeVO> getAllEmployees(Long orgId);

	@Query(value ="select l.alcount,l.clcount,l.cocount,l.mlcount,l.plcount,l.slcount,l.ulcount from employeeleavedetails l\r\n"
			+ "where orgid=?1 and employeeid=?2",nativeQuery =true)
	Set<Object[]> findEmployeeLeaveCount(Long orgId, Long empId);

}
