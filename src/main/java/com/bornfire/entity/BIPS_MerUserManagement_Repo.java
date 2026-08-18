package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BIPS_MerUserManagement_Repo extends JpaRepository<BIPS_Mer_User_Management_Entity, String> {
	
	@Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT where del_flag1='N'", nativeQuery = true)
	 List<BIPS_Mer_User_Management_Entity> getUserManage();
	 
	 @Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT where user_id=?1  and del_flag1='N'", nativeQuery = true)
	 BIPS_Mer_User_Management_Entity getuser(String user_id);

	 @Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT where merchant_user_id=?1   and del_flag1='N'", nativeQuery = true)
	 List<BIPS_Mer_User_Management_Entity> getMer(String merchant_user_id);
	 
	 @Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT where merchant_user_id=?1   and del_flag1='N'", nativeQuery = true)
	 List<BIPS_Mer_User_Management_Entity> getUserManage1(String merchant_user_id);
	 
	 @Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT where merchant_user_id=?1   and del_flag1='N'", nativeQuery = true)
	 BIPS_Mer_User_Management_Entity getuserdata(String merchant_user_id);

	@Query(value = "select count(*) from BIPS_MERCHANT_USER_MANAGEMENT where merchant_user_id=?1  and del_flag1='N'", nativeQuery = true)
	Integer getUserscount(String merchant_user_id);
	
	@Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT  where user_id=?1 and entry_flag='N'  and del_flag1='N'", nativeQuery = true)
	BIPS_Mer_User_Management_Entity getbyflg(String usersid);
	
	@Query(value = "select  DISTINCT user_id FROM BIPS_MERCHANT_USER_MANAGEMENT WHERE merchant_user_id =?1  and del_flag1='N'", nativeQuery = true)
	List<String> getuserid(String merchant_id1);
	
	 @Query(value = "select * from BIPS_MERCHANT_USER_MANAGEMENT where merchant_user_id=?1 and unit_id_u=?2  and del_flag1='N'", nativeQuery = true)
	 List<BIPS_Mer_User_Management_Entity> getUserManageId(String merchant_user_id,String unit_id_u);
	 
	 @Query(value = "SELECT * FROM BIPS_MERCHANT_USER_MANAGEMENT WHERE user_id=?1 ", nativeQuery = true)
	 BIPS_Mer_User_Management_Entity getrole(String userid);
}