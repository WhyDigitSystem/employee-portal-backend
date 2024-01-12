package com.whydigit.efit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.LeaveBalanceVO;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalanceVO, Integer> {

	Optional<LeaveBalanceVO> findByEmpcode(String empcode);

}
