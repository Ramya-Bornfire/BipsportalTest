package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceReqRepository extends JpaRepository<ServiceReqEntity,String>{
	@Query(value = "select * from SERVICE_REQUEST_MONITORING WHERE merchant_id=?1", nativeQuery = true)
	List<ServiceReqEntity> findByAll(String merchant_id);
	
	@Query(value = "select * from SERVICE_REQUEST_MONITORING WHERE merchant_id=?1 AND unit_id=?2", nativeQuery = true)
	List<ServiceReqEntity> findByunitAll(String merchant_id, String unitid);
	
	@Query(value = "SELECT * FROM SERVICE_REQUEST_MONITORING WHERE (:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_id = :merchantUserId AND unit_id = :unitid)", nativeQuery = true)
	List<ServiceReqEntity> findByAllMerchantId(String merchantUserId, String unitid);
	
	@Query(value = "select * from SERVICE_REQUEST_MONITORING order by REQUEST_ID desc", nativeQuery = true)
	List<ServiceReqEntity> desclist();
}

