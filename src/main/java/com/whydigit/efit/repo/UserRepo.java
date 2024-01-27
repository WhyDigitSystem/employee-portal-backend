package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.whydigit.efit.entity.UserVO;

public interface UserRepo extends JpaRepository<UserVO, Long> {

	boolean existsByEmail(String email);

	UserVO findByEmail(String email);

	@Query(value = "select u from UserVO u where u.id =?1")
	UserVO getUserById(Long userId);

	UserVO findByEmailAndUserId(String email, Long userId);

}