package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.UserRoleVO;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRoleVO, Integer> {

}
