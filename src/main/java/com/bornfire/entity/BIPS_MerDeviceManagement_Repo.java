package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface BIPS_MerDeviceManagement_Repo extends JpaRepository<BIPS_Mer_Device_Management_Entity, String>{
	

	@Query(value = "select * from BIPS_MERCHANT_DEVICE_MANAGEMENT ", nativeQuery = true)
	 List<BIPS_Mer_Device_Management_Entity> getDeviceManage();

	 @Query(value = "select * from BIPS_MERCHANT_DEVICE_MANAGEMENT where device_id=?1 ", nativeQuery = true)
	 BIPS_Mer_Device_Management_Entity getdevice(String device_id);

	 @Query(value = "select * from BIPS_MERCHANT_DEVICE_MANAGEMENT where merchant_user_id=?1 ", nativeQuery = true)
	 BIPS_Mer_Device_Management_Entity getdevi(String merchant_user_id);
	 
	 @Query(value = "select * from BIPS_MERCHANT_DEVICE_MANAGEMENT where merchant_user_id=?1 ", nativeQuery = true)
	 List<BIPS_Mer_Device_Management_Entity> getaddDevice(String merchant_user_id);
	 
	@Query(value = "select count(*) from BIPS_MERCHANT_DEVICE_MANAGEMENT where merchant_user_id=?1", nativeQuery = true)
	Integer getDevicecount(String merchant_user_id);
	 
	@Query(value = "select * from BIPS_MERCHANT_DEVICE_MANAGEMENT  where device_id=?1 and entry_flag='N'", nativeQuery = true)
	BIPS_Mer_Device_Management_Entity getbyflg(String usersid);
	
	@Query(value = "select  DISTINCT device_id FROM BIPS_MERCHANT_DEVICE_MANAGEMENT WHERE merchant_user_id =?1", nativeQuery = true)
	List<String> getdeviceId(String merchant_id1);

	 @Query(value = "select * from BIPS_MERCHANT_DEVICE_MANAGEMENT where merchant_user_id=?1 and unit_id_d=?2", nativeQuery = true)
	 List<BIPS_Mer_Device_Management_Entity> getaddDeviceId(String merchant_user_id, String unit_id_d);
}