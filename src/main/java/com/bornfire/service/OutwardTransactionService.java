package com.bornfire.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bornfire.entity.AllCustomTransaction;
import com.bornfire.entity.BIPS_Charge_Back_Entity;
import com.bornfire.entity.BIPS_Charge_Back_Rep;
import com.bornfire.entity.ChargesBacksEntity;
import com.bornfire.entity.CustomerPayResponse;
import com.bornfire.entity.DeviceManagementRepository;
import com.bornfire.entity.FeesAndChargesEntity;
import com.bornfire.entity.MerchantMasterRep;
import com.bornfire.entity.OutwardTransactionMonitoringTable;
import com.bornfire.entity.OutwardTransactionMonitoringTableRep;

@Service
public class OutwardTransactionService {

	private final OutwardTransactionMonitoringTableRep repository;

	@Autowired
	MerchantMasterRep merchantrepo;
	
	@Autowired
	BIPS_Charge_Back_Rep bIPS_Charge_Back_Rep;
	
	@Autowired
	DeviceManagementRepository devicerepo;

	@Autowired
	public OutwardTransactionService(OutwardTransactionMonitoringTableRep repository) {
		this.repository = repository;
	}
	
	public OutwardTransactionMonitoringTable getCustomerTransactionView(String message_ref) {
		OutwardTransactionMonitoringTable transaction = repository.findByMessageRef(message_ref);
		return transaction;
	}
	List<Object[]> transactions;
	
	//All Customer transaction
	public List<AllCustomTransaction> getAllCustomerTransaction(String merchant_id, String unit_id,String fromdate, String todate,String type) {
		if(type.equals("MOBILEVIEW"))
		{
			transactions = repository.findByAllCustomeroneDayTranId(merchant_id, unit_id,fromdate, todate);
		}
		else {
			transactions = repository.findByAllCustomerTranId(merchant_id, unit_id,fromdate, todate);
		}
		if (transactions.isEmpty()) {
			return Collections.emptyList();
		} else {
			return transactions.stream().map(this::mapToCusomDataList).collect(Collectors.toList());
		}

	}

	private AllCustomTransaction mapToCusomDataList(Object[] transaction) {
		return new AllCustomTransaction(transaction[0], transaction[1], transaction[2], transaction[3], transaction[4],
				transaction[5], transaction[6], transaction[7], transaction[8], transaction[9], transaction[10],
				transaction[11], transaction[12], transaction[13], transaction[14], transaction[15], transaction[16],
				transaction[17], transaction[18], transaction[19], transaction[20]);
	}
	
	
	//All Fees and Charge
	public List<FeesAndChargesEntity> getAllFeesChargesTransactions(String merchant_id, String unit_id,String fromdate, String todate,String type) {
		if(type.equals("MOBILEVIEW"))
		{
			transactions = repository.findByAllFeesOnedayTranId(merchant_id, unit_id, fromdate, todate);
		}
		else {
		 transactions = repository.findByAllFeesTranId(merchant_id, unit_id, fromdate, todate);
		}
		if (transactions.isEmpty()) {
			return Collections.emptyList();
		} else {
			return transactions.stream().map(this::mapToFeesCharge).collect(Collectors.toList());
		}
	}
	
	private FeesAndChargesEntity mapToFeesCharge(Object[] transaction) {
		return new FeesAndChargesEntity(transaction[0], transaction[1], transaction[2],transaction[3], transaction[4],transaction[5],transaction[6],      
				transaction[7],transaction[8], transaction[9], transaction[10],transaction[11],transaction[12], transaction[13],
				transaction[14],transaction[15],transaction[16],transaction[17],transaction[18],transaction[19],transaction[20],transaction[21],transaction[22]        
);
	}

	// All Charge Back Merchant wise
	public List<ChargesBacksEntity> getAllChargeBacks(String merchant_id) {
		List<OutwardTransactionMonitoringTable> transactions = repository.findByMerchantId(merchant_id);
		if (transactions.isEmpty()) {
			// System.out.println("No Chargeback found for merchantid: " + merchant_id);
			return Collections.emptyList();
		} else {
			return transactions.stream().map(this::mapToChargeBack).collect(Collectors.toList());
		}

	}

	// All Charge Back Unit wise
	public List<ChargesBacksEntity> getAllUnitChargeBacks(String merchant_id, String unit_id) {
		List<OutwardTransactionMonitoringTable> transactions = repository.findByUnitId(merchant_id, unit_id);
		if (transactions.isEmpty()) {
			// System.out.println("No Chargeback found for merchantid: " + merchant_id);
			return Collections.emptyList();
		} else {
			return transactions.stream().map(this::mapToChargeBack).collect(Collectors.toList());
		}

	}

	private ChargesBacksEntity mapToChargeBack(OutwardTransactionMonitoringTable transaction) {
		return new ChargesBacksEntity(transaction.getTran_date(), transaction.getSequence_unique_id(),
				transaction.getTran_audit_number(), transaction.getMerchant_bill_number(), transaction.getBill_date(),
				transaction.getBill_amount(), transaction.getTran_currency(), transaction.getReversal_remarks(),
				transaction.getReversal_date(), transaction.getReversal_amount()

		);
	}

	public OutwardTransactionMonitoringTable getFeesChargeForOne(String message_ref) {
		OutwardTransactionMonitoringTable transactions = repository.findByMessageRef(message_ref);
		return transactions;
	}

////All Customer Transaction unit wise for Admin
	public CustomerPayResponse getCustomerPayDetails(String merchant_id, String device_id, String referencenumber) {
		OutwardTransactionMonitoringTable transactions = repository.findeCustomerPayDetails(merchant_id, device_id,
				referencenumber);
		List<Object[]> merchantmaster = merchantrepo.getpaymentrecipt(merchant_id);
		String termnialid = devicerepo.findByTerminalId(device_id);
		if (Objects.isNull(transactions)) {
			// System.out.println("No transactions found for merchantid: " + merchant_id +
			// "device_id" + device_id);
			return null;
		} else {
			return mapTocustomerdata(transactions, merchantmaster, termnialid);
		}
	}

	private CustomerPayResponse mapTocustomerdata(OutwardTransactionMonitoringTable transaction,
			List<Object[]> merchantmaster, String terminalid) {

		Object[] firstEntry = merchantmaster.get(0);
		return new CustomerPayResponse(transaction.getMerchant_id(), transaction.getMerchant_loyalty_number(),
				transaction.getTran_status(), transaction.getCbs_status(), transaction.getTran_amount(),
				transaction.getMerchant_ref_label(), transaction.getSequence_unique_id(), transaction.getTran_date(),
				firstEntry[1] != null ? firstEntry[1].toString() : null,
				firstEntry[2] != null ? firstEntry[2].toString() : null,
				firstEntry[3] != null ? firstEntry[3].toString() : null, terminalid);
	}

	public BIPS_Charge_Back_Entity getChargeBackForOne(String message_ref) {
		BIPS_Charge_Back_Entity transactions = bIPS_Charge_Back_Rep.getTransactionDetailByUSeqId(message_ref);
		return transactions;
	}


	public OutwardTransactionMonitoringTable getTransactionForOne(String message_ref) {
		OutwardTransactionMonitoringTable transactions = repository.getTransactionDetailByUSeqId(message_ref);
		return transactions;
	}}
