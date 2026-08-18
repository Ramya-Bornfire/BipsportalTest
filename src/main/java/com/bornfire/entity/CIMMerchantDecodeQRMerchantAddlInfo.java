package com.bornfire.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({"billNumber","mobileNumber","storeLabel","loyaltyNumber","referenceLabel",
	"customerLabel","terminalLabel","purposeOfTransaction","addlDataRequest"})
public class CIMMerchantDecodeQRMerchantAddlInfo {
	private String BillNumber;

	private String MobileNumber;
	
	private String StoreLabel;
	
	private String LoyaltyNumber;

	private String ReferenceLabel;
	
	private String CustomerLabel;
	
	private String TerminalLabel;

	private String PurposeOfTransaction;
	
	private String AddlDataRequest;

	public String getBillNumber() {
		return BillNumber;
	}

	public void setBillNumber(String billNumber) {
		BillNumber = billNumber;
	}

	public String getMobileNumber() {
		return MobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		MobileNumber = mobileNumber;
	}

	public String getStoreLabel() {
		return StoreLabel;
	}

	public void setStoreLabel(String storeLabel) {
		StoreLabel = storeLabel;
	}

	public String getLoyaltyNumber() {
		return LoyaltyNumber;
	}

	public void setLoyaltyNumber(String loyaltyNumber) {
		LoyaltyNumber = loyaltyNumber;
	}

	public String getReferenceLabel() {
		return ReferenceLabel;
	}

	public void setReferenceLabel(String referenceLabel) {
		ReferenceLabel = referenceLabel;
	}

	public String getCustomerLabel() {
		return CustomerLabel;
	}

	public void setCustomerLabel(String customerLabel) {
		CustomerLabel = customerLabel;
	}

	public String getTerminalLabel() {
		return TerminalLabel;
	}

	public void setTerminalLabel(String terminalLabel) {
		TerminalLabel = terminalLabel;
	}

	public String getPurposeOfTransaction() {
		return PurposeOfTransaction;
	}

	public void setPurposeOfTransaction(String purposeOfTransaction) {
		PurposeOfTransaction = purposeOfTransaction;
	}

	public String getAddlDataRequest() {
		return AddlDataRequest;
	}

	public void setAddlDataRequest(String addlDataRequest) {
		AddlDataRequest = addlDataRequest;
	}
	
	
	
	
}
