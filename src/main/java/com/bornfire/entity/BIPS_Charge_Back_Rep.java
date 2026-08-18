package com.bornfire.entity;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface BIPS_Charge_Back_Rep extends JpaRepository<BIPS_Charge_Back_Entity, String> {

	@Query(value = "select * from BIPS_CHARGE_BACK_TABLE where sequence_unique_id=?1", nativeQuery = true)
	BIPS_Charge_Back_Entity getTransactionDetailByUSeqId(String sequence_unique_id);
	
	@Query(value = "SELECT * FROM BIPS_CHARGE_BACK_TABLE WHERE merchant_id=?1", nativeQuery = true)
	List<BIPS_Charge_Back_Entity> getAllListMerchant(String merchant_id);

	@Query(value = "SELECT * FROM BIPS_CHARGE_BACK_TABLE WHERE merchant_id=?1 AND unit_id=?2", nativeQuery = true)
	List<BIPS_Charge_Back_Entity> getAllListUnit(String merchant_id, String unitid);
	
	@Query(value = "SELECT * FROM BIPS_CHARGE_BACK_TABLE WHERE merchant_id=?1 AND unit_id=?2 and  reversal_remarks='REVERTED'", nativeQuery = true)
	List<BIPS_Charge_Back_Entity> getAllListUnitRevert(String merchant_id, String unitid);
	
	@Query(value = "SELECT * FROM BIPS_CHARGE_BACK_TABLE WHERE merchant_id=?1 AND unit_id=?2 and  reversal_remarks='PENDING'", nativeQuery = true)
	List<BIPS_Charge_Back_Entity> getAllListUnitPending(String merchant_id, String unitid);
	
	@Query(value = "select * from BIPS_CHARGE_BACK_TABLE where reversal_remarks ='REVERTED' AND  merchant_id=?1", nativeQuery = true)
	List<BIPS_Charge_Back_Entity> getRevertedTransactionMerchantList(String merchant_id);

	@Query(value = "select * from BIPS_CHARGE_BACK_TABLE where reversal_remarks ='REVERTED' AND  merchant_id=?1 AND unit_id=?2", nativeQuery = true)
	List<BIPS_Charge_Back_Entity> getRevertedTransactionUnitList(String merchant_id,String unit);

	@Query(value = "select * from BIPS_CHARGE_BACK_TABLE where reversal_remarks <>'REVERTED' AND  merchant_id=?1", nativeQuery = true)
	List<BIPS_Charge_Back_Entity> getPendingTransactionMerchantList(String merchant_id);

	@Query(value = "select * from BIPS_CHARGE_BACK_TABLE where reversal_remarks <>'REVERTED' AND  merchant_id=?1 AND unit_id=?2", nativeQuery = true)
	List<BIPS_Charge_Back_Entity> getPendingTransactionUnitList(String merchant_id,String unit );
	
	@Query(value = "SELECT * FROM BIPS_CHARGE_BACK_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY') AND (:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_id = :merchantUserId AND unit_id = :unitid)", nativeQuery = true)
	List<BIPS_Charge_Back_Entity> getAllListchargeback(String merchantUserId, String unitid,String fromdate, String todate);
	
	@Query(value = "SELECT * FROM BIPS_CHARGE_BACK_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY')  AND  reversal_remarks ='REVERTED' AND (:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_id = :merchantUserId AND unit_id = :unitid)", nativeQuery = true)
	List<BIPS_Charge_Back_Entity> getAllRevertedTransaction(String merchantUserId,String unitid, String fromdate, String todate);

	@Query(value = "SELECT * FROM BIPS_CHARGE_BACK_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY')  AND reversal_remarks <>'REVERTED' AND (:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_id = :merchantUserId AND unit_id = :unitid)", nativeQuery = true)
	List<BIPS_Charge_Back_Entity> getAllPendingTransaction(String merchantUserId,String unitid, String fromdate, String todate);
	
	
	//For Web Application Don't touch this
		@Query(value = "select * from BIPS_CHARGE_BACK_TABLE WHERE merchant_id=?1 and trunc(tran_date) BETWEEN trunc(to_date(?2, 'DD-MON-YYYY')) AND trunc(to_date(?3, 'DD-MON-YYYY'))", nativeQuery = true)
		List<BIPS_Charge_Back_Entity> findAllTansactionBetweenDates(String merchant_id,String from_date,String to_date);
		
		@Query(value = "select * from BIPS_CHARGE_BACK_TABLE WHERE merchant_id=?1 and reversal_remarks=?2 and trunc(tran_date) BETWEEN trunc(to_date(?3, 'DD-MON-YYYY')) AND trunc(to_date(?4, 'DD-MON-YYYY'))", nativeQuery = true)
		List<BIPS_Charge_Back_Entity> findAllTansactionBetweenDates(String merchant_id,String tran_type,String from_date,String to_date);
		
		@Query(value = "select * from BIPS_CHARGE_BACK_TABLE WHERE merchant_id=?1 and unit_id=?2 and trunc(tran_date) BETWEEN trunc(to_date(?3, 'DD-MON-YYYY')) AND trunc(to_date(?4, 'DD-MON-YYYY'))", nativeQuery = true)
		List<BIPS_Charge_Back_Entity> findAllUnitTansactionBetweenDates(String merchant_id,String unit_id,String from_date,String to_date);
		
		@Query(value = "select * from BIPS_CHARGE_BACK_TABLE WHERE merchant_id=?1 and reversal_remarks=?2 and unit_id=?3 and trunc(tran_date) BETWEEN trunc(to_date(?4, 'DD-MON-YYYY')) AND trunc(to_date(?5, 'DD-MON-YYYY'))", nativeQuery = true)
		List<BIPS_Charge_Back_Entity> findAllUnitTansactionBetweenDates(String merchant_id,String tran_type,String unit_id,String from_date,String to_date);

		@Query(value = "SELECT * FROM BIPS_CHARGE_BACK_TABLE WHERE merchant_id=?1 and  reversal_remarks='REVERTED'", nativeQuery = true)
		List<BIPS_Charge_Back_Entity> getAllListMerRevert(String merchant_id);
		
		@Query(value = "SELECT * FROM BIPS_CHARGE_BACK_TABLE WHERE merchant_id=?1 and  reversal_remarks='PENDING'", nativeQuery = true)
		List<BIPS_Charge_Back_Entity> getAllListMerPending(String merchant_id);
		
		// New Query (29-10-2024) for charge back
		
		@Query(value = "SELECT * FROM BIPS_CHARGE_BACK_TABLE WHERE merchant_id=?1 AND unit_id=?2 and  reversal_remarks='PENDING' and trunc(tran_date) BETWEEN trunc(to_date(?3, 'DD-MON-YYYY')) AND trunc(to_date(?4, 'DD-MON-YYYY'))", nativeQuery = true)
		List<BIPS_Charge_Back_Entity> getAllListUnitPendingForWeb(String merchant_id, String unitid,String currDate,String currDate1);
		
		@Query(value = "SELECT * FROM BIPS_CHARGE_BACK_TABLE WHERE merchant_id=?1 AND reversal_remarks='PENDING' and trunc(tran_date) BETWEEN trunc(to_date(?2, 'DD-MON-YYYY')) AND trunc(to_date(?3, 'DD-MON-YYYY'))", nativeQuery = true)
		List<BIPS_Charge_Back_Entity> getAllListMerchatAdminPending(String merchant_id,String currDate,String currDate1);
		
}
