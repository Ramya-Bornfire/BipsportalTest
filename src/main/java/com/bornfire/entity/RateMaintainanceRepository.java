package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RateMaintainanceRepository extends JpaRepository<RateMaintainanceEntity,String>{

	@Query(value = "select * from BIPS_RATEMAINT", nativeQuery = true)
	List<RateMaintainanceEntity> findByAll();
	
	@Query(value="select * from BIPS_RATEMAINT WHERE SRL=?1",nativeQuery=true)
	RateMaintainanceEntity findByMerchantUserId(String merchantUserId);
	
	@Query(value = "SELECT SequenceForRate.NEXTVAL FROM dual", nativeQuery = true)
	String getRequestUUID();
}
