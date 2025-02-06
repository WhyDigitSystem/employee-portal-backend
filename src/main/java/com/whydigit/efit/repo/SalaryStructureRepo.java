package com.whydigit.efit.repo;

import java.util.List;import java.util.Map;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.whydigit.efit.entity.SalaryStructureVO;

public interface SalaryStructureRepo extends JpaRepository<SalaryStructureVO, Long> {

	List<SalaryStructureVO> findByOrgId(Long orgId);

	@Query(nativeQuery = true,value = "SELECT \r\n"
			+ "    a.orgid,\r\n"
			+ "    a.empcode,\r\n"
			+ "    a.empname,\r\n"
			+ "    a.totaldays monthdays, \r\n"
			+ "    a.totalleaves,\r\n"
			+ "    a.lop,\r\n"
			+ "    (a.totaldays - a.lop) AS effectiveworkingdays,\r\n"
			+ "    b.netpay,\r\n"
			+ "    ROUND((b.netpay / a.totaldays), 2) AS perdaysalary,  -- Per day salary (2 decimal places)\r\n"
			+ "    ROUND((a.totaldays - a.lop) * (b.netpay / a.totaldays), 2) AS finalsalary,  -- Final salary (2 decimal places)\r\n"
			+ "    ROUND(a.lop * (b.netpay / a.totaldays), 2) AS lopamount,ROW_NUMBER() OVER () id  -- LOP Amount (2 decimal places)\r\n"
			+ "FROM monthlyattendance a,\r\n"
			+ "(SELECT * FROM (\r\n"
			+ "    SELECT *, \r\n"
			+ "           ROW_NUMBER() OVER (PARTITION BY orgid, employeecode ORDER BY createdon DESC) AS rn\r\n"
			+ "    FROM salarystructure \r\n"
			+ ") AS ranked\r\n"
			+ "WHERE rn = 1 AND orgid = ?1) b\r\n"
			+ "WHERE a.orgid = b.orgid and a.salarymonth=?2 and a.year=?3\r\n"
			+ "AND a.empcode = b.employeecode and a.empcode not in(\r\n"
			+ "select employeecode from salaryprocess where orgid=?1 and salarymonth=?2 and year=?3 group by employeecode)")
	Set<Object> getSalaryProcessDetails(Long orgId, String month, String year);

	@Query(nativeQuery = true,value = "SELECT * FROM (\r\n"
			+ "    SELECT *, \r\n"
			+ "           ROW_NUMBER() OVER (PARTITION BY orgid, employeecode ORDER BY createdon DESC) AS rn\r\n"
			+ "    FROM salarystructure \r\n"
			+ ") AS ranked\r\n"
			+ "WHERE rn = 1 AND orgid = ?1 and employeecode=?2")
	SalaryStructureVO getLatestSalaryStructureDetails(Long orgId, String empCode);
	
	
	@Query(nativeQuery = true,value = "select n.orgid,n.employeecode,n.employeename,0 totalleavedays,0 lop,n.position,n.department,n.bankaccountno,n.location,n.pan,ROW_NUMBER() OVER () id,n.dateofbirth,n.dateofjoining,n.grade,n.uan,n.bankname,sum(n.netpay),case when sum(n.netpay)>0 then 'View' else 'Pending' end salarystatus,case when SUM(n.netpay) > 0 then 'Revise' else 'Add' end action from(\r\n"
			+ "SELECT a.account_no bankaccountno,a.bank_name bankname,a.date_of_birth dateofbirth,a.joining_date dateofjoining,a.department,a.empcode employeecode,a.empname employeename,a.grade,a.branch location,a.pan,a.uan,a.designation position,a.org_id orgid,0 netpay FROM employee_details a where a.active=1 and a.org_id=?1\r\n"
			+ "union\r\n"
			+ "SELECT b.bankaccountno,b.bankname,b.dateofbirth,b.dateofjoining,b.department,b.employeecode,b.employeename,b.garde grade,b.location,b.pan,b.uan,b.position,b.orgid,b.netpay FROM (\r\n"
			+ "    SELECT *, \r\n"
			+ "           ROW_NUMBER() OVER (PARTITION BY v.orgid, v.employeecode ORDER BY v.createdon DESC) AS rn\r\n"
			+ "    FROM salarystructure v \r\n"
			+ ") AS b\r\n"
			+ "WHERE rn = 1 AND b.orgid = ?1) n group by n.bankaccountno,n.bankname,n.dateofbirth,n.dateofjoining,n.department,n.employeecode,n.employeename,n.grade,n.location,n.pan,n.uan,n.position,n.orgid order by n.employeecode asc")
	Set<Object> getSalaryStructureRegister(Long orgId);


}
