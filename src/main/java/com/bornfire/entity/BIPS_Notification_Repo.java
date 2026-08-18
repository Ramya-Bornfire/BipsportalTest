package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BIPS_Notification_Repo extends JpaRepository<BIPS_Notification_Entity, String>{

	@Query(value = "SELECT DISTINCT * FROM bips_notification", nativeQuery = true)
	List<BIPS_Notification_Entity> getlst();
	
}
