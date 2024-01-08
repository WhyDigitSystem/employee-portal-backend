package com.whydigit.efit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.LeaveRequestVO;

public interface LeaveRequestRepo extends JpaRepository<LeaveRequestVO, Integer> {

	List<LeaveRequestVO> findByEmpcode(String empcode);

}
