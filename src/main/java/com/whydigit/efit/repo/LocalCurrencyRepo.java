package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.LocalCurrencyVO;

@Repository
public interface LocalCurrencyRepo extends JpaRepository<LocalCurrencyVO, Integer>{

}
