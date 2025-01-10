package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.SalaryMasterVO;

public interface SalaryMasterRepo extends JpaRepository<SalaryMasterVO, Long> {

}
