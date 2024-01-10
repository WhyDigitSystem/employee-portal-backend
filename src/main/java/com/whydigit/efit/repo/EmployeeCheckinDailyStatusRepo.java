package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.EmployeeCheckinDailyStatusVO;

public interface EmployeeCheckinDailyStatusRepo extends JpaRepository<EmployeeCheckinDailyStatusVO, String> {

}
