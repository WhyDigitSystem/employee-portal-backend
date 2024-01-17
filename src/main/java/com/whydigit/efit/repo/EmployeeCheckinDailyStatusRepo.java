package com.whydigit.efit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.whydigit.efit.entity.EmployeeCheckinDailyStatusVO;

public interface EmployeeCheckinDailyStatusRepo extends JpaRepository<EmployeeCheckinDailyStatusVO, String> {

	
	EmployeeCheckinDailyStatusVO findByEmpcode(String empcode);

}
