package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.MonthlyAttendanceVO;

public interface MonthlyAttendanceRepo extends JpaRepository<MonthlyAttendanceVO, Long> {

}
