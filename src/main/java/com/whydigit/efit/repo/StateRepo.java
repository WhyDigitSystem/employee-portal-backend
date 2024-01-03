package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.StateVO;

@Repository
public interface StateRepo extends JpaRepository<StateVO, Integer> {

}
