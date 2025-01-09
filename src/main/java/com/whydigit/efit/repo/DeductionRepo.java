package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.DeductionsVO;

public interface DeductionRepo extends JpaRepository<DeductionsVO, Long> {

}
