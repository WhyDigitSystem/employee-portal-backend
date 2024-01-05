package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.PermissionRequestVO;

public interface PermissionRequestRepo extends JpaRepository<PermissionRequestVO, Integer> {

}
