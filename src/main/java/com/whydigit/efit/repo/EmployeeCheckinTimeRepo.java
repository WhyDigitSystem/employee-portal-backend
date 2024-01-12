package com.whydigit.efit.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.whydigit.efit.entity.EmployeeCheckInTimeVO;

public interface EmployeeCheckinTimeRepo extends JpaRepository<EmployeeCheckInTimeVO, Integer>{

	
	List<EmployeeCheckInTimeVO> findByEmpcode(String empcode);

}
