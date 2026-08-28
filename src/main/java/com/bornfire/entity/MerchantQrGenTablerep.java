package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantQrGenTablerep extends JpaRepository<MerchantQrGenTable, String> {

	@Query(value = "select * from BIPS_MERCHANT_QRCODE_GEN_TABLE where p_id=?1 Union all select * from BIPS_MERCHANT_QRCODE_GEN_HIST_TABLE where p_id=?1", nativeQuery = true)
	List<Object[]> existsByPID(String p_id);

	@Query(value = "select * from BIPS_MERCHANT_QRCODE_GEN_TABLE where p_id=?1 Union all select * from BIPS_MERCHANT_QRCODE_GEN_HIST_TABLE where p_id=?1", nativeQuery = true)
	List<MerchantQrGenTable> findByPId(String p_id);
	
	/*
	 * @Query(
	 * value="select bill_number from BIPS_MERCHANT_QRCODE_GEN_TABLE WHERE bill_number=?1"
	 * ,nativeQuery=true) String findByBilNumber(String billnumber);
	 */
	
	@Query(value="select * from BIPS_MERCHANT_QRCODE_GEN_TABLE WHERE (reference_label = ?1 OR p_id = ?1) and ROWNUM = 1",nativeQuery=true)
	MerchantQrGenTable getRecordByRefLable(String refLable);
	
}
