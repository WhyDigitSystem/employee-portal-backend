package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.CompanySetupVO;


@Repository
public interface CompanySetupRepo extends JpaRepository<CompanySetupVO, Integer> {

}
