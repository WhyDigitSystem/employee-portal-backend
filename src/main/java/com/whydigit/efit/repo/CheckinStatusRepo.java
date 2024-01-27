package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.CheckinStatusVO;

public interface CheckinStatusRepo extends JpaRepository<CheckinStatusVO, String> {

}
