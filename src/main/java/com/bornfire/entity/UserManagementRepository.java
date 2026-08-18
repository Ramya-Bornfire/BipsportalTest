package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserManagementRepository extends JpaRepository<UserManagementEntity, String> {

	@Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT where merchant_user_id=?1 AND del_flag1='N'", nativeQuery = true)
	List<UserManagementEntity> findByAll(String merchantUserId);

	@Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT WHERE user_id=?1 AND del_flag1='N'", nativeQuery = true)
	UserManagementEntity findByMerchantUserId(String merchantUserId);

	@Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT WHERE merchant_user_id=?1 AND unit_id_u=?2 AND del_flag1='N'", nativeQuery = true)
	List<UserManagementEntity> findByAllUnit(String merchantUserId, String UnitId);

	@Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT where merchant_user_id=?1 AND del_flag1='N'", nativeQuery = true)
	List<UserManagementEntity> getUserManage1(String merchant_user_id);

	@Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT where merchant_user_id=?1 AND del_flag1='N'", nativeQuery = true)
	UserManagementEntity getuserdata(String merchant_user_id);

	@Query(value = "select count(*) from BIPS_MERCHANT_USER_MANAGEMENT where merchant_user_id=?1 AND del_flag1='N'", nativeQuery = true)
	Integer getUserscount(String merchant_user_id);

	@Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT  where user_id=?1 and entry_flag='N' AND del_flag1='N'", nativeQuery = true)
	UserManagementEntity getbyflg(String usersid);

	@Query(value = "select  DISTINCT user_id FROM BIPS_MERCHANT_USER_MANAGEMENT WHERE merchant_user_id =?1 AND del_flag1='N'", nativeQuery = true)
	List<String> getuserid(String merchant_id1);

	@Query(value = "select  * FROM BIPS_MERCHANT_USER_MANAGEMENT WHERE merchant_user_id =?1 AND del_flag1='N' order by user_id desc", nativeQuery = true)
	List<UserManagementEntity> getuserid1(String merchant_id1);

	@Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT where merchant_user_id=?1 and unit_id_u=?2 AND del_flag1='N'", nativeQuery = true)
	List<UserManagementEntity> getUserManageId(String merchant_user_id, String unit_id_u);

	@Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT WHERE user_id=?1 AND del_flag1='N'", nativeQuery = true)
	UserManagementEntity findByIdCustom(String merchantUserId);

	@Query(value = "SELECT * FROM BIPS_MERCHANT_USER_MANAGEMENT WHERE del_flag1='N' AND ((:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_user_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_user_id = :merchantUserId AND unit_id_u = :unitid))", nativeQuery = true)
	List<UserManagementEntity> findMerchantandUnit(String merchantUserId, String unitid);
	
	@Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT where merchant_user_id=?1 and unit_id_u=?2 AND del_flag1='N'", nativeQuery = true)
	List<UserManagementEntity> findByUnitandMerchant(String merchantUserId,String unit_id);
}
