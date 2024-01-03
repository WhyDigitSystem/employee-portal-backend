package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.CountryListVO;

@Repository
public interface CountryListRepo extends JpaRepository<CountryListVO, Integer>{

}
