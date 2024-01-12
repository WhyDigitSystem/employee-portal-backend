package com.whydigit.efit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.whydigit.efit.dto.ResponseDTO;
import com.whydigit.efit.entity.PermissionRequestVO;

public interface PermissionRequestRepo extends JpaRepository<PermissionRequestVO, Integer> {

	

}
