package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.TokenVO;

@Repository
public interface TokenRepo extends JpaRepository<TokenVO, String>{

	
}
