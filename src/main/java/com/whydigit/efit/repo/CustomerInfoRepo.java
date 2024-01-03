package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.CustomerInfoVO;


@Repository
public interface CustomerInfoRepo extends JpaRepository<CustomerInfoVO, Integer> {

}


