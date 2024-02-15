package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.BranchVO;

public interface BranchRepo extends JpaRepository<BranchVO, Long> {

}
