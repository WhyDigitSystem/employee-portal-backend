package com.whydigit.efit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.LeaveTypeVO;
import com.whydigit.efit.entity.PermissionRequestVO;

@Repository
public interface LeaveTypeRepo extends JpaRepository<LeaveTypeVO, Integer> {

	

}
