package com.whydigit.efit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.LeaveBalanceVO;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalanceVO, Integer> {

	
	List<LeaveBalanceVO> findByEmpcode(String empcode);

}
