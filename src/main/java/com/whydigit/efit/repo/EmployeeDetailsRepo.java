package com.whydigit.efit.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.dto.logincreationDTO;
import com.whydigit.efit.entity.EmployeeDetailsVO;

@Repository
public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetailsVO, Integer> {

	

	@Query(nativeQuery = true,value="SELECT a.employee_code,a.email,a.role from employee_details a")
	Optional<logincreationDTO> findByEmail();


	

	


	

}
