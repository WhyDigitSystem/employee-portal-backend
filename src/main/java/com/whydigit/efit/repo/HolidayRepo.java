package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.HolidayVO;
@Repository
public interface HolidayRepo extends JpaRepository<HolidayVO, Integer>{

}