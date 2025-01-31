package com.whydigit.efit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.RequestLeaveVO;

@Repository
public interface RequestLeaveRepo extends JpaRepository<RequestLeaveVO, Long>{

	@Query(value ="select * from requestleave where orgid and empid",nativeQuery =true)
	List<RequestLeaveVO> findAllRequestLeave(Long orgId,Long empId);

}
