package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<NotificationEntity, String> {
	

	@Query(value = "SELECT * FROM NOTIFICATION_PARM_MASTER WHERE MERCHANTID=?1", nativeQuery = true)
	List<NotificationEntity> getmerchantnotification(String merchant_id);
	
	@Query(value = "SELECT * FROM NOTIFICATION_PARM_MASTER WHERE MERCHANTID=?1 AND UNITID=?2", nativeQuery = true)
	List<NotificationEntity> getunitnotification(String merchant_id, String unitid);
	
	@Query(value = "SELECT * FROM NOTIFICATION_PARM_MASTER WHERE (:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchantid = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchantid = :merchantUserId AND unitid = :unitid)", nativeQuery = true)
	List<NotificationEntity> getallnotification(String merchantUserId, String unitid);
	
	@Query(value = "SELECT * FROM NOTIFICATION_PARM_MASTER order by record_srl_no desc", nativeQuery = true)
	List<NotificationEntity> descList();
}

