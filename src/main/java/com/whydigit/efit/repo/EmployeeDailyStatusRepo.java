package com.whydigit.efit.repo;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.whydigit.efit.entity.EmployeeDailyStatusVO;

public interface EmployeeDailyStatusRepo extends JpaRepository<EmployeeDailyStatusVO, Long> {

	@Query(value = "SELECT count(e) FROM EmployeeDailyStatusVO e WHERE e.empCode = ?3 AND CAST(e.loginDate AS LocalDate) BETWEEN ?1 AND ?2")
	long getPresentDays(LocalDate startDate, LocalDate endDate,String empCode);

}
