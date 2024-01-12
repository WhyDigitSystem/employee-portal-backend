package com.whydigit.efit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.PermissionReportVO;

public interface PermissionReportRepository extends JpaRepository<PermissionReportVO, Integer>{

	List<PermissionReportVO> findByEmpcode(String empcode);

}
