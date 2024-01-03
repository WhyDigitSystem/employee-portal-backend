
package com.whydigit.efit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whydigit.efit.entity.CurrencyMasterVO;


@Repository
public interface CurrencyMasterRepo extends JpaRepository<CurrencyMasterVO, Integer> {

}
