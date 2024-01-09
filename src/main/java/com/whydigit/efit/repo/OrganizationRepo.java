package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.OrganizationVO;

public interface OrganizationRepo extends JpaRepository<OrganizationVO, Long>{

	boolean existsByName(String name);

}
