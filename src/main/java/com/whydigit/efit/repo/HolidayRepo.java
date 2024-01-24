package com.whydigit.efit.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.HolidayVO;

@Repository
public interface HolidayRepo extends JpaRepository<HolidayVO, Integer> {

	@Query(value = "SELECT h FROM HolidayVO h WHERE h.department = ?1 AND h.orgId = ?2 AND h.holiday_date BETWEEN ?3 AND ?4")
	List<HolidayVO> getHolidaysByDepAndCompanyCode(String department, long orgId, LocalDate startDate,
			LocalDate endDate);

	@Query(value = "SELECT COUNT(h) FROM HolidayVO h WHERE h.department = ?1 AND h.orgId = ?2 AND h.holiday_date BETWEEN ?3 AND ?4")
	long getNoOfHolidaysByDepAndCompanyCode(String department, long orgId, LocalDate startDate, LocalDate endDate);

}
