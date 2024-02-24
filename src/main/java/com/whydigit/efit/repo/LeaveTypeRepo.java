package com.whydigit.efit.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.LeaveTypeVO;

@Repository
public interface LeaveTypeRepo extends JpaRepository<LeaveTypeVO, Integer> {

	@Query("select e.leave_type,e.leave_code from LeaveTypeVO e")
	Set<Object[]> findAllType();

	

}
