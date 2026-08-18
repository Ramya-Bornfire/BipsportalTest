package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaticMerchantNotificationRepo extends JpaRepository<StaticMerchantNotificationEntity, String>{
	
	@Query(value = "SELECT STATIC_SEQ_NEW.NEXTVAL FROM DUAL", nativeQuery = true)
	String getUnicStaticId();
	

	@Query(value = "SELECT * FROM Static_Merchant_Notification where merchant_id=?1 AND device_id=?2 AND user_id=?3 AND notification_flag='N'", nativeQuery = true)
	List<StaticMerchantNotificationEntity> getstaticqrdetails(String merchantid, String deviceid, String userid);

}
