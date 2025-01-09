package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.EarningsVO;

public interface EarningsRepo extends JpaRepository<EarningsVO, Long> {

}
