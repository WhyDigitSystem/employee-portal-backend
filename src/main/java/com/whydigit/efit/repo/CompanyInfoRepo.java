package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.CompanyInfoVO;




@Repository
public interface CompanyInfoRepo extends JpaRepository<CompanyInfoVO, Integer> {

}
