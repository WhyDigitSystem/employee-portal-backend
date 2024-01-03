package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.CompanySetVO;



@Repository
public interface CompanySetRepo extends JpaRepository<CompanySetVO, Integer> {

}
