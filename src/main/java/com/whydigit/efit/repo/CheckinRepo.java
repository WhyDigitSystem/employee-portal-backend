package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whydigit.efit.entity.CheckinVO;

public interface CheckinRepo extends JpaRepository<CheckinVO, Integer> {
	
//	@Query(nativeQuery = true,value = "select a.id,a.check_in_time,a.check_out_time,a.checkin,a.userid from checkintime a where a.userid=@userid and date(a.checkin)=date(sysdate())")
//	CheckinVO findByUserId(@Param("userid") String userid);

}
