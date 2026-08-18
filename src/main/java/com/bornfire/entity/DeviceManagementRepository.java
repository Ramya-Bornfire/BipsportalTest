package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceManagementRepository extends JpaRepository<DeviceManagementEntity,String> {

		@Query(value = "select * from  BIPS_MERCHANT_DEVICE_MANAGEMENT WHERE merchant_user_id=?1 AND del_flg='N'", nativeQuery = true)
		List<DeviceManagementEntity> findByAll(String merchant);
		
		@Query(value="select * from BIPS_MERCHANT_DEVICE_MANAGEMENT WHERE device_identification_no=?1 AND del_flg='N'",nativeQuery=true)
		DeviceManagementEntity findByMerchantUserId(String merchantUserId);
		
		@Query(value = "select * from  BIPS_MERCHANT_DEVICE_MANAGEMENT WHERE merchant_user_id=?1 AND unit_id_d=?2 AND del_flg='N'", nativeQuery = true)
		List<DeviceManagementEntity> findByAllUnit(String merchant, String unit_id);
		
		 @Query(value = "select * from BIPS_MERCHANT_DEVICE_MANAGEMENT where device_identification_no=?1  AND del_flg='N'", nativeQuery = true)
		 DeviceManagementEntity getdevice(String device_id);
		 
		 @Query(value = "select * from BIPS_MERCHANT_DEVICE_MANAGEMENT where device_id=?1  AND del_flg='N'", nativeQuery = true)
		 DeviceManagementEntity getdeviceWeb(String device_id);
		 
		 @Query(value = "select * from BIPS_MERCHANT_DEVICE_MANAGEMENT where device_id=?1", nativeQuery = true)
		 DeviceManagementEntity getdeviceWebForDelete(String device_id);

		 @Query(value = "select * from BIPS_MERCHANT_DEVICE_MANAGEMENT where merchant_user_id=?1  AND del_flg='N'", nativeQuery = true)
		 DeviceManagementEntity getdevi(String merchant_user_id);
		 
		 @Query(value = "select * from BIPS_MERCHANT_DEVICE_MANAGEMENT where merchant_user_id=?1 AND del_flg='N' order by device_id desc", nativeQuery = true)
		 List<DeviceManagementEntity> getaddDevice(String merchant_user_id);
		 
		@Query(value = "select count(*) from BIPS_MERCHANT_DEVICE_MANAGEMENT where merchant_user_id=?1 AND del_flg='N'", nativeQuery = true)
		Integer getDevicecount(String merchant_user_id);
		 
		@Query(value = "select * from BIPS_MERCHANT_DEVICE_MANAGEMENT  where device_id=?1 and entry_flag='N' AND del_flg='N'", nativeQuery = true)
		DeviceManagementEntity getbyflg(String usersid);
		
		@Query(value = "select  DISTINCT device_id FROM BIPS_MERCHANT_DEVICE_MANAGEMENT WHERE merchant_user_id =?1 AND del_flg='N'", nativeQuery = true)
		List<String> getdeviceId(String merchant_id1);

		 @Query(value = "select * from BIPS_MERCHANT_DEVICE_MANAGEMENT where merchant_user_id=?1 and unit_id_d=?2 AND del_flg='N'", nativeQuery = true)
		 List<DeviceManagementEntity> getaddDeviceId(String merchant_user_id, String unit_id_d);
		 
		@Query(value="select terminal_id from BIPS_MERCHANT_DEVICE_MANAGEMENT WHERE device_identification_no=?1 AND del_flg='N'",nativeQuery=true)
		String findByTerminalId(String merchantUserId);
		
		@Query(value="select device_identification_no from BIPS_MERCHANT_DEVICE_MANAGEMENT WHERE device_identification_no=?1 AND del_flg='N'",nativeQuery=true)
		String findBydeviceId(String merchantUserId);
		
		@Query(value = "select  *FROM BIPS_MERCHANT_DEVICE_MANAGEMENT WHERE merchant_user_id =?1 AND del_flg='N'", nativeQuery = true)
		List<DeviceManagementEntity> getdeviceId1(String merchant_id1);
		
		@Query(value = "SELECT * FROM BIPS_MERCHANT_DEVICE_MANAGEMENT WHERE del_flg='N' AND( (:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_user_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_user_id = :merchantUserId AND unit_id_d = :unitid))", nativeQuery = true)
		List<DeviceManagementEntity> findAllDevice(String merchantUserId, String unitid);
		
	}
