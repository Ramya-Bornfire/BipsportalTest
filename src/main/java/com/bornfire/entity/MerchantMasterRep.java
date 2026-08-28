package com.bornfire.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface MerchantMasterRep extends JpaRepository<MerchantMaster, String> {
Optional<MerchantMaster> findById(String directorId);
	
	@Query(value = "select * from MERCHANT_MASTER_TABLE ", nativeQuery = true)
	List<MerchantMaster> findAllCustom();
	
	@Query(value = "select * from MERCHANT_MASTER_TABLE where del_flg='N' ", nativeQuery = true)
	List<MerchantMaster> findAllData();

	@Query(value = "select * from MERCHANT_MASTER_TABLE where merchant_id= ?1 and del_flg='N' and ROWNUM = 1", nativeQuery = true)
	MerchantMaster findByIdCustom(String Id);
	
	@Query(value = "select * from MERCHANT_MASTER_TABLE  where del_flg ='N'  UNION ALL select * from MERCHANT_MASTER_TABLE_MOD  where entity_flg ='N'", nativeQuery = true)
	List<MerchantMaster> ALLDATA();
	
	@Query(value = "select * from MERCHANT_MASTER_TABLE where merchant_id= ?1", nativeQuery = true)
	List<MerchantMaster> ALLDATAs(String Id);
	
	@Query(value = "select * from MERCHANT_MASTER_TABLE  where entity_flg ='Y' and del_flg ='N'  and merchant_pow_ca_no like %:mpcn% and merchant_legal_id like %:mlid% and merchant_name like  %:mn% UNION ALL select * from MERCHANT_MASTER_TABLE_MOD  where entity_flg ='N' and merchant_pow_ca_no like %:mpcn% and merchant_legal_id like %:mlid% and merchant_name like  %:mn%", nativeQuery = true)
	List<MerchantMaster> ALLDATASEARCH(String mpcn,String mlid,String mn);
	
	@Query(value = "select * from MERCHANT_MASTER_TABLE where merchant_id=?1 ", nativeQuery = true)
	MerchantMaster getMerlst(String Id);
	
	@Query(value = "select * from MERCHANT_MASTER_TABLE where merchant_legal_id=?1 and merchant_corp_name =?2 and merchant_name=?3 and mer_user_id_r1=?4 ", nativeQuery = true)
	List<MerchantMaster> merctopas(String a, String b,String c,String d);
	
	@Query(value = "select * from MERCHANT_MASTER_TABLE where merchant_legal_id =?1", nativeQuery = true)
	MerchantMaster getMerchantPass(String merchant_legal_id);
	
	@Query(value = "select * from MERCHANT_MASTER_TABLE where merchant_id=?1", nativeQuery = true)
	List<MerchantMaster> merModi(String Id);
	
	@Query(value = "select merchant_acc_no from MERCHANT_MASTER_TABLE  where merchant_id=?1 " , nativeQuery = true)
	String getMerchantaccountnumber(String merchantid);

	@Query(value = "select merchant_cat_code from MERCHANT_MASTER_TABLE  where merchant_id=?1 " , nativeQuery = true)
	String getMCC(String merchantid);
	
	@Query(value = "select merchant_id, merchant_name,merchant_addr,merchant_city from MERCHANT_MASTER_TABLE  where merchant_id=?1 " , nativeQuery = true)
	List<Object[]> getpaymentrecipt(String merchantid);
	
	@Query(value = "select TRANSACTION_AMOUNT from MERCHANT_MASTER_TABLE  where merchant_id=?1 " , nativeQuery = true)
	String getTranAmountLimit(String merchantid);
	
}