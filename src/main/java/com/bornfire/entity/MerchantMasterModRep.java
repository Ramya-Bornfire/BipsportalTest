package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface MerchantMasterModRep extends JpaRepository<MerchantMasterMod, String> {
	
	@Query(value = "select * from MERCHANT_MASTER_TABLE_MOD where merchant_id= ?1", nativeQuery = true)
	MerchantMasterMod findByIdCustom(String Id);

	@Query(value = "select * from MERCHANT_MASTER_TABLE_MOD where merchant_legal_id= ?1", nativeQuery = true)
	MerchantMasterMod findByIdCustom1(String Id);
	
	@Query(value = "select * from MERCHANT_MASTER_TABLE_MOD where merchant_legal_id=?1 and merchant_corp_name =?2 and merchant_name=?3 and mer_user_id_r1=?4 ", nativeQuery = true)
	List<MerchantMasterMod> merctopas(String a, String b,String c,String d);
}