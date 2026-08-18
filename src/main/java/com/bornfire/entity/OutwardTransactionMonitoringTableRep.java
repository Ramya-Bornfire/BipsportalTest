package com.bornfire.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OutwardTransactionMonitoringTableRep extends JpaRepository<OutwardTransactionMonitoringTable, String> {

	@Query(value = "select * from bips_outward_transaction_monitoring_table WHERE TRUNC(tran_date) <> TO_DATE(?1, 'DD-MM-YYYY')", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> getOutDateRecord(String currentDate);

	@Query(value = "SELECT a.merchant_Addr, a.merchant_Location, a.merchant_acc_no, a.currency, b.tran_date, b.sequence_unique_id, b.MERCHANT_BILL_NUMBER,b.MERCHANT_STORE_LABEL, b.MERCHANT_CUSTOMER_LABEL, b.merchant_loyalty_number, b.INITIATOR_BANK, b.tran_amount, b.tran_status FROM BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE b JOIN merchant_master_table a ON b.merchant_id = a.merchant_id WHERE b.merchant_id = ?1 AND TRUNC(b.tran_date) BETWEEN TO_DATE(?2, 'DD-MM-YYYY') AND TO_DATE(?3, 'DD-MM-YYYY') union all SELECT a.merchant_Addr, a.merchant_Location, a.merchant_acc_no, a.currency, b.tran_date, b.sequence_unique_id, b.MERCHANT_BILL_NUMBER,b.MERCHANT_STORE_LABEL, b.MERCHANT_CUSTOMER_LABEL, b.merchant_loyalty_number, b.INITIATOR_BANK, b.tran_amount, b.tran_status FROM BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE b JOIN merchant_master_table a ON b.merchant_id = a.merchant_id WHERE b.merchant_id = ?1 AND TRUNC(b.tran_date) BETWEEN TO_DATE(?2, 'DD-MM-YYYY') AND TO_DATE(?3, 'DD-MM-YYYY')", nativeQuery = true)
	List<Object[]> findByMerchantTransactionsDateRange(String merchantUserId, String startDate, String endDate);

	@Query(value = "SELECT a.merchant_Addr, a.merchant_Location, a.merchant_acc_no, a.currency, b.tran_date, b.sequence_unique_id, b.MERCHANT_BILL_NUMBER,b.MERCHANT_STORE_LABEL, b.MERCHANT_CUSTOMER_LABEL, b.merchant_loyalty_number, b.INITIATOR_BANK, b.tran_amount, b.tran_status FROM BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE b JOIN merchant_master_table a ON b.merchant_id = a.merchant_id WHERE b.merchant_id= ?1 AND TRUNC(b.tran_date) BETWEEN TO_DATE(?2, 'DD-MM-YYYY') AND TO_DATE(?3, 'DD-MM-YYYY') AND b.MERCHANT_STORE_LABEL=?4 uninon all SELECT a.merchant_Addr, a.merchant_Location, a.merchant_acc_no, a.currency, b.tran_date, b.sequence_unique_id, b.MERCHANT_BILL_NUMBER,b.MERCHANT_STORE_LABEL, b.MERCHANT_CUSTOMER_LABEL, b.merchant_loyalty_number, b.INITIATOR_BANK, b.tran_amount, b.tran_status FROM BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE b JOIN merchant_master_table a ON b.merchant_id = a.merchant_id WHERE b.merchant_id= ?1 AND TRUNC(b.tran_date) BETWEEN TO_DATE(?2, 'DD-MM-YYYY') AND TO_DATE(?3, 'DD-MM-YYYY') AND b.MERCHANT_STORE_LABEL=?4", nativeQuery = true)
	List<Object[]> findByMerchantUnitTransactionsDateRange(String merchantUserId, String startDate, String endDate,
			String UnitId);

	@Query(value = "SELECT a.merchant_Addr, a.merchant_Location, a.merchant_acc_no, a.currency, b.tran_date, b.sequence_unique_id, b.MERCHANT_BILL_NUMBER,b.MERCHANT_STORE_LABEL, b.MERCHANT_CUSTOMER_LABEL, b.merchant_loyalty_number, b.INITIATOR_BANK, b.tran_amount, b.tran_status FROM BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE b JOIN merchant_master_table a ON b.merchant_id = a.merchant_id WHERE b.merchant_id= ?1 AND TRUNC(b.tran_date) BETWEEN TO_DATE(?2, 'DD-MM-YYYY') AND TO_DATE(?3, 'DD-MM-YYYY') AND b.MERCHANT_STORE_LABEL=?4 And b.MERCHANT_CUSTOMER_LABEL=?5 union all SELECT a.merchant_Addr, a.merchant_Location, a.merchant_acc_no, a.currency, b.tran_date, b.sequence_unique_id, b.MERCHANT_BILL_NUMBER,b.MERCHANT_STORE_LABEL, b.MERCHANT_CUSTOMER_LABEL, b.merchant_loyalty_number, b.INITIATOR_BANK, b.tran_amount, b.tran_status FROM BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE b JOIN merchant_master_table a ON b.merchant_id = a.merchant_id WHERE b.merchant_id= ?1 AND TRUNC(b.tran_date) BETWEEN TO_DATE(?2, 'DD-MM-YYYY') AND TO_DATE(?3, 'DD-MM-YYYY') AND b.MERCHANT_STORE_LABEL=?4 And b.MERCHANT_CUSTOMER_LABEL=?5", nativeQuery = true)
	List<Object[]> findByUserTransactionsDateRange(String merchantUserId, String startDate, String endDate, String UnitId,
			String Userid);

	@Query(value = "SELECT a.merchant_Addr, a.merchant_Location, a.merchant_acc_no, a.currency, b.tran_date, b.sequence_unique_id, b.MERCHANT_BILL_NUMBER,b.MERCHANT_STORE_LABEL, b.MERCHANT_CUSTOMER_LABEL, b.merchant_loyalty_number, b.INITIATOR_BANK, b.tran_amount, b.tran_status FROM BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE b JOIN merchant_master_table a ON b.merchant_id = a.merchant_id WHERE b.merchant_id= ?1 AND TRUNC(b.tran_date) BETWEEN TO_DATE(?2, 'DD-MM-YYYY') AND TO_DATE(?3, 'DD-MM-YYYY') AND b.MERCHANT_STORE_LABEL=?4 And b.merchant_loyalty_number=?5 union all SELECT a.merchant_Addr, a.merchant_Location, a.merchant_acc_no, a.currency, b.tran_date, b.sequence_unique_id, b.MERCHANT_BILL_NUMBER,b.MERCHANT_STORE_LABEL, b.MERCHANT_CUSTOMER_LABEL, b.merchant_loyalty_number, b.INITIATOR_BANK, b.tran_amount, b.tran_status FROM BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE b JOIN merchant_master_table a ON b.merchant_id = a.merchant_id WHERE b.merchant_id= ?1 AND TRUNC(b.tran_date) BETWEEN TO_DATE(?2, 'DD-MM-YYYY') AND TO_DATE(?3, 'DD-MM-YYYY') AND b.MERCHANT_STORE_LABEL=?4 And b.merchant_loyalty_number=?5", nativeQuery = true)
	List<Object[]> findByDeviceTransactionsDateRange(String merchantUserId, String startDate, String endDate, String UnitId,
			String deviceID);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) WHERE sequence_unique_id=?1", nativeQuery = true)
	OutwardTransactionMonitoringTable findByMerchantUserId(String merchantUserId);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) WHERE merchant_id=?1 AND MERCHANT_STORE_LABEL=?2", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findByUnitId(String merchantUserId, String unit_id);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) WHERE merchant_id=?1", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findByMerchantId(String merchantUserId);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where  merchant_id=?1 AND merchant_loyalty_number=?2 AND MERCHANT_REF_LABEL=?3", nativeQuery = true)
	OutwardTransactionMonitoringTable findeCustomerPayDetails(String merchantid, String deviceid,
			String referencenumber);

	@Query(value = "select * from BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE where p_id=?1 Union all select * from BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE where p_id=?1", nativeQuery = true)
	List<Object[]> existsByPID(String p_id);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) WHERE merchant_loyalty_number=?1 AND MERCHANT_ID=?2", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findTransactionById(String user_id, String Merchant_id);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) WHERE MERCHANT_CUSTOMER_LABEL=?1 AND merchant_loyalty_number=?2 AND MERCHANT_ID=?3", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findTansactionUserById(String user_id, String device_id,
			String Merchant_id);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where sequence_unique_id=?1", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> getExistData(String seqID);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where master_ref_id=?1", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findBulkCreditID(String master_ref_id);

	@Query(value = "update BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE set  where master_ref_id=?1", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> updateBulkCreditCBSStatusError(String master_ref_id);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where master_ref_id=?1", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findBulkDebitID(String master_ref_id);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where instr_id=?1 or end_end_id=?2", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> getRTPIncomindCreditExist(String instrID, String endToEndID008);

	@Modifying
	@Query(value = "update BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE set cbs_status=?2,tran_audit_number=?3 where sequence_unique_id=?1", nativeQuery = true)
	void updateCbsData(String endToEndID008, String cbsStatus, String auditNumber);

	@Modifying
	@Query(value = "update BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE set tran_audit_number=?2 where sequence_unique_id=?1", nativeQuery = true)
	void updateAuditTranID(String endToEndID008, String sysTraceNumber008);

	@Modifying
	@Query(value = "update BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE set cim_cnf_request_uid=?2,cim_cnf_status=?3,cim_cnf_status_error=?4 where sequence_unique_id=?1", nativeQuery = true)
	void updateCIMCNFData(String seqUniqueID, String requestUUID, String status, String statusError);

	@Modifying
	@Query(value = "update BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE set ipsx_status=?2,ipsx_response_time=?3 where sequence_unique_id=?1", nativeQuery = true)
	void updateIPSXStatusBulkRTP(String seqUniqueID, String ipsStatus, String ipsStatusDate);

	@Modifying
	@Query(value = "update BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE set tran_audit_number=?1,Cbs_status=?3,cbs_status_error=?4,cbs_response_time=?5 where sequence_unique_id=?2", nativeQuery = true)
	void updateCBSStatusRTPError(String sysTraceNumber008, String endToEndID008, String cbsStatus, String error_desc,
			String cbsResTime);

	@Query(value = "select tran_status,DECODE(IPSX_STATUS_ERROR,'',CBS_STATUS_ERROR,IPSX_STATUS_ERROR),SEQUENCE_UNIQUE_ID,P_ID from BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE where SEQUENCE_UNIQUE_ID=?1 Union all select tran_status,DECODE(IPSX_STATUS_ERROR,'',CBS_STATUS_ERROR,IPSX_STATUS_ERROR),SEQUENCE_UNIQUE_ID,P_ID from BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE where SEQUENCE_UNIQUE_ID=?1", nativeQuery = true)
	List<Object[]> existsByTranID(String p_id);

	@Query(value = "select tran_status,DECODE(IPSX_STATUS_ERROR,'',CBS_STATUS_ERROR,IPSX_STATUS_ERROR),SEQUENCE_UNIQUE_ID,P_ID from BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE where P_ID=?1 Union all select tran_status,DECODE(IPSX_STATUS_ERROR,'',CBS_STATUS_ERROR,IPSX_STATUS_ERROR),SEQUENCE_UNIQUE_ID,P_ID  from BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE where P_ID=?1", nativeQuery = true)
	List<Object[]> existsByRefID(String p_id);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where sequence_unique_id=?1", nativeQuery = true)
	OutwardTransactionMonitoringTable getTransactionDetailByUSeqId(String sequence_unique_id);

	@Query(value = "select * from (select * from BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE union all select * from BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE) WHERE sequence_unique_id=?1", nativeQuery = true)
	OutwardTransactionMonitoringTable findByMessageRef(String message_ref);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where merchant_id = ?1", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findTransactionByIdRep(String merchantId);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) WHERE (:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_id = :merchantUserId AND unit_id = :unitid)", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findByAllMerchantUnitId(String merchantUserId, String unitid);

	// For Web Application Don't touch this
	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where merchant_id=?1 and trunc(tran_date) BETWEEN trunc(to_date(?2, 'DD-MON-YYYY')) AND trunc(to_date(?3	, 'DD-MON-YYYY'))", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findAllTansactionBetweenDates(String merchant_id, String from_date,
			String to_date);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where merchant_id=?1 and tran_status=?2 and trunc(tran_date) BETWEEN trunc(to_date(?3, 'DD-MON-YYYY')) AND trunc(to_date(?4, 'DD-MON-YYYY'))", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findAllTansactionBetweenDates(String merchant_id, String tran_type,
			String from_date, String to_date);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where merchant_id=?1 and unit_id=?2 and trunc(tran_date) BETWEEN trunc(to_date(?3, 'DD-MON-YYYY')) AND trunc(to_date(?4, 'DD-MON-YYYY'))", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findAllUnitTansactionBetweenDates(String merchant_id, String unit_id,
			String from_date, String to_date);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where merchant_id=?1 and tran_status=?2 and unit_id=?3 and trunc(tran_date) BETWEEN trunc(to_date(?4, 'DD-MON-YYYY')) AND trunc(to_date(?5, 'DD-MON-YYYY'))", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findAllUnitTansactionBetweenDates(String merchant_id, String tran_type,
			String unit_id, String from_date, String to_date);

	@Query(value = "SELECT tran_date, sequence_unique_id, initiator_bank, tran_currency, tran_amount, tran_status, tran_rmks, ipsx_account, cim_account, ipsx_account_name, cim_account_name, part_tran_type, merchant_id, reversal_remarks, merchant_bill_number, reversal_date, reversal_amount, auth_user, auth_time, user_id, user_name FROM BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY') AND ((:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_id = :merchantUserId AND unit_id = :unitid)) UNION ALL SELECT tran_date, sequence_unique_id, initiator_bank, tran_currency, tran_amount, tran_status, tran_rmks, ipsx_account, cim_account, ipsx_account_name, cim_account_name, part_tran_type, merchant_id, reversal_remarks, merchant_bill_number, reversal_date, reversal_amount, auth_user, auth_time, user_id, user_name FROM BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY') AND ((:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_id = :merchantUserId AND unit_id = :unitid)) ORDER BY tran_date DESC, sequence_unique_id DESC FETCH FIRST 100 ROWS ONLY", nativeQuery = true)
	List<Object[]> findByAllCustomeroneDayTranId(String merchantUserId, String unitid, String fromdate, String todate);

	@Query(value = "SELECT tran_date, sequence_unique_id, initiator_bank, tran_currency, tran_amount, tran_status, tran_rmks, ipsx_account, cim_account, ipsx_account_name, cim_account_name, part_tran_type, merchant_id, reversal_remarks, merchant_bill_number, reversal_date, reversal_amount, auth_user, auth_time, charge_app_flg, conv_fee, user_id, user_name FROM BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY') AND ((:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_id = :merchantUserId AND unit_id = :unitid)) UNION ALL SELECT tran_date, sequence_unique_id, initiator_bank, tran_currency, tran_amount, tran_status, tran_rmks, ipsx_account, cim_account, ipsx_account_name, cim_account_name, part_tran_type, merchant_id, reversal_remarks, merchant_bill_number, reversal_date, reversal_amount, auth_user, auth_time, charge_app_flg, conv_fee, user_id, user_name FROM BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY') AND ((:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_id = :merchantUserId AND unit_id = :unitid)) ORDER BY tran_date DESC, sequence_unique_id DESC FETCH FIRST 100 ROWS ONLY", nativeQuery = true)
	List<Object[]> findByAllFeesOnedayTranId(String merchantUserId, String unitid, String fromdate, String todate);

	@Query(value = "SELECT tran_date, sequence_unique_id, initiator_bank, tran_currency, tran_amount, tran_status, tran_rmks, ipsx_account, cim_account, ipsx_account_name, cim_account_name, part_tran_type, merchant_id, reversal_remarks, merchant_bill_number, reversal_date, reversal_amount, auth_user, auth_time, user_id, user_name FROM BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY') AND ((:merchantUserId IS NOT NULL AND :userid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :userid IS NOT NULL AND merchant_id = :merchantUserId AND user_id = :userid)) UNION ALL SELECT tran_date, sequence_unique_id, initiator_bank, tran_currency, tran_amount, tran_status, tran_rmks, ipsx_account, cim_account, ipsx_account_name, cim_account_name, part_tran_type, merchant_id, reversal_remarks, merchant_bill_number, reversal_date, reversal_amount, auth_user, auth_time, user_id, user_name FROM BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY') AND ((:merchantUserId IS NOT NULL AND :userid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :userid IS NOT NULL AND merchant_id = :merchantUserId AND user_id = :userid)) ORDER BY tran_date DESC, sequence_unique_id DESC FETCH FIRST 100 ROWS ONLY", nativeQuery = true)
	List<Object[]> findAllTansactionOnedayById(String userid, String merchantUserId, String fromdate, String todate);

	@Query(value = "SELECT tran_date, sequence_unique_id, initiator_bank, tran_currency, tran_amount, tran_status, tran_rmks, ipsx_account, cim_account, ipsx_account_name, cim_account_name, part_tran_type, merchant_id, reversal_remarks, merchant_bill_number, reversal_date, reversal_amount, auth_user, auth_time, user_id, user_name FROM BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY') AND ((:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_id = :merchantUserId AND unit_id = :unitid)) UNION ALL SELECT tran_date, sequence_unique_id, initiator_bank, tran_currency, tran_amount, tran_status, tran_rmks, ipsx_account, cim_account, ipsx_account_name, cim_account_name, part_tran_type, merchant_id, reversal_remarks, merchant_bill_number, reversal_date, reversal_amount, auth_user, auth_time, user_id, user_name FROM BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY') AND ((:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_id = :merchantUserId AND unit_id = :unitid))", nativeQuery = true)
	List<Object[]> findByAllCustomerTranId(String merchantUserId, String unitid,String fromdate, String todate);

	@Query(value = "SELECT tran_date, sequence_unique_id, initiator_bank, tran_currency, tran_amount, tran_status, tran_rmks, ipsx_account, cim_account, ipsx_account_name, cim_account_name, part_tran_type, merchant_id, reversal_remarks, merchant_bill_number, reversal_date, reversal_amount, auth_user, auth_time, charge_app_flg, conv_fee, user_id, user_name FROM BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY') AND ((:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_id = :merchantUserId AND unit_id = :unitid)) UNION ALL SELECT tran_date, sequence_unique_id, initiator_bank, tran_currency, tran_amount, tran_status, tran_rmks, ipsx_account, cim_account, ipsx_account_name, cim_account_name, part_tran_type, merchant_id, reversal_remarks, merchant_bill_number, reversal_date, reversal_amount, auth_user, auth_time, charge_app_flg, conv_fee, user_id, user_name FROM BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY') AND ((:merchantUserId IS NOT NULL AND :unitid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :unitid IS NOT NULL AND merchant_id = :merchantUserId AND unit_id = :unitid))", nativeQuery = true)
	List<Object[]> findByAllFeesTranId(String merchantUserId, String unitid, String fromdate, String todate);
	
	@Query(value = "SELECT tran_date, sequence_unique_id, initiator_bank, tran_currency, tran_amount, tran_status, tran_rmks, ipsx_account, cim_account, ipsx_account_name, cim_account_name, part_tran_type, merchant_id, reversal_remarks, merchant_bill_number, reversal_date, reversal_amount, auth_user, auth_time, user_id, user_name FROM BIPS_OUTWARD_TRANSACTION_MONITORING_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY') AND ((:merchantUserId IS NOT NULL AND :userid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :userid IS NOT NULL AND merchant_id = :merchantUserId AND user_id = :userid)) UNION ALL SELECT tran_date, sequence_unique_id, initiator_bank, tran_currency, tran_amount, tran_status, tran_rmks, ipsx_account, cim_account, ipsx_account_name, cim_account_name, part_tran_type, merchant_id, reversal_remarks, merchant_bill_number, reversal_date, reversal_amount, auth_user, auth_time, user_id, user_name FROM BIPS_OUTWARD_TRANSACTION_HIST_MONITORING_TABLE WHERE trunc(tran_date) BETWEEN TO_DATE(:fromdate, 'DD-MM-YYYY') AND TO_DATE(:todate, 'DD-MM-YYYY') AND ((:merchantUserId IS NOT NULL AND :userid IS NULL AND merchant_id = :merchantUserId) OR (:merchantUserId IS NOT NULL AND :userid IS NOT NULL AND merchant_id = :merchantUserId AND user_id = :userid))", nativeQuery = true)
	List<Object[]> findAllTansactionById(String userid, String merchantUserId, String fromdate, String todate);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where merchant_id=?1 and trunc(tran_date) = trunc(to_date(?2, 'DD-MON-YYYY'))", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findAllTransactionsInSingleDate(String merchantId, String date);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where merchant_id=?1 and trunc(tran_date) = trunc(to_date(?2, 'DD-MON-YYYY')) and unit_id=?3", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findAllTransactionsInSingleDate(String merchantId, String date, String unitId);
	
	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where trunc(tran_date) = trunc(to_date(?2, 'DD-MON-YYYY')) and user_id=?1", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> userTransactionsInSingleDate(String user_id, String date);
	
	@Query(value="select merchant_bill_number from (select merchant_bill_number from bips_outward_transaction_monitoring_table union all select merchant_bill_number from bips_outward_transaction_hist_monitoring_table) where merchant_bill_number=?1",nativeQuery=true)
	String findByBilNumber(String billnumber);
	
	// For Web charge back (29-10-2024)
	
	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where merchant_id=?1 and unit_id=?2 and trunc(tran_date) BETWEEN trunc(to_date(?3, 'DD-MON-YYYY')) AND trunc(to_date(?4, 'DD-MON-YYYY'))", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findAllUnitTansactionBetweenDatesForChargeBack(String merchant_id, String unit_id,String from_date, String to_date);

	@Query(value = "select * from (select * from bips_outward_transaction_monitoring_table union all select * from bips_outward_transaction_hist_monitoring_table) where merchant_id=?1 and trunc(tran_date) BETWEEN trunc(to_date(?2, 'DD-MON-YYYY')) AND trunc(to_date(?3	, 'DD-MON-YYYY'))", nativeQuery = true)
	List<OutwardTransactionMonitoringTable> findAllTansactionBetweenDatesForChargeBack(String merchant_id, String from_date,String to_date);
	
}
