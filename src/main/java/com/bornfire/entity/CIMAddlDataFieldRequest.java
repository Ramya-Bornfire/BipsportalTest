package com.bornfire.entity;

import javax.validation.constraints.Size;

public class CIMAddlDataFieldRequest {
	@Size(max = 25, message = "BillNumber should not exceed 25 characters")
	private String BillNumber;
	
	@Size(max = 25, message = "MobileNumber should not exceed 25 characters")
	private String MobileNumber;
	@Size(max = 25, message = "Reference Label should not exceed 25 characters")
	private String ReferenceLabel;
	@Size(max = 25, message = "PurposeOfTransaction should not exceed 25 characters")
	private String PurposeOfTransaction;
	@Size(max = 25, message = "StoreLabel should not exceed 25 characters")
	private String StoreLabel;
	@Size(max = 25, message = "LoyaltyNumber should not exceed 25 characters")
	private String LoyaltyNumber;
	@Size(max = 25, message = "DeviceID should not exceed 25 characters")
	private String DeviceID;
	@Size(max = 25, message = "CustomerLabel should not exceed 25 characters")
	private String CustomerLabel;
	@Size(max = 25, message = "TerminalLabel should not exceed 25 characters")
	private String TerminalLabel;
	
	@Size(max = 25, message = "AddlDataRequest should not exceed 25 characters")
	private String AddlDataRequest;
	
	
	public String getLoyaltyNumber() {
		return LoyaltyNumber;
	}
	public void setLoyaltyNumber(String loyaltyNumber) {
		LoyaltyNumber = loyaltyNumber;
	}
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
	public String getReferenceLabel() {
		return ReferenceLabel;
	}
	public void setReferenceLabel(String referenceLabel) {
		ReferenceLabel = referenceLabel;
	}
	public String getPurposeOfTransaction() {
		return PurposeOfTransaction;
	}
	public void setPurposeOfTransaction(String purposeOfTransaction) {
		PurposeOfTransaction = purposeOfTransaction;
	}
	public String getStoreLabel() {
		return StoreLabel;
	}
	public void setStoreLabel(String storeLabel) {
		StoreLabel = storeLabel;
	}
	public String getDeviceID() {
		return DeviceID;
	}
	public void setDeviceID(String deviceID) {
		DeviceID = deviceID;
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
	public String getAddlDataRequest() {
		return AddlDataRequest;
	}
	public void setAddlDataRequest(String addlDataRequest) {
		AddlDataRequest = addlDataRequest;
	}

}
