package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.EmployeeDetailsVO;

@Repository
public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetailsVO, Integer> {

}