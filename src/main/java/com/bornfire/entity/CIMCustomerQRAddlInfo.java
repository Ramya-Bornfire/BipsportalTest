package com.bornfire.entity;

import javax.validation.constraints.Size;

public class CIMCustomerQRAddlInfo {
	@Size(max = 25, message = "BillNumber should not exceed 25 characters")
	private String BillNumber;
	@Size(max = 25, message = "MobileNumber should not exceed 25 characters")
	private String MobileNumber;
	@Size(max = 25, message = "StoreLabel should not exceed 25 characters")
	private String StoreLabel;
	@Size(max = 25, message = "LoyaltyNumber should not exceed 25 characters")
	private String ReferenceNumber;
	@Size(max = 25, message = "deviceID should not exceed 25 characters")
	private String DeviceID;
	@Size(max = 25, message = "CustomerLabel should not exceed 25 characters")
	private String CustomerLabel;
	@Size(max = 25, message = "TerminalLabel should not exceed 25 characters")
	private String TerminalLabel;
	@Size(max = 25, message = "PurposeOfTransaction should not exceed 25 characters")
	private String PurposeOfTransaction;
	@Size(max = 25, message = "AddlDataRequest should not exceed 25 characters")
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
	public String getReferenceNumber() {
		return ReferenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		ReferenceNumber = referenceNumber;
	}
	public String getdeviceID() {
		return DeviceID;
	}
	public void setdeviceID(String deviceID) {
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
	@Override
	public String toString() {
		return "CIMMerchantQRAddlInfo [BillNumber=" + BillNumber + ", MobileNumber=" + MobileNumber + ", StoreLabel="
				+ StoreLabel + ", ReferenceNumber=" + ReferenceNumber + ", DeviceID=" + DeviceID
				+ ", CustomerLabel=" + CustomerLabel + ", TerminalLabel=" + TerminalLabel + ", PurposeOfTransaction="
				+ PurposeOfTransaction + ", AddlDataRequest=" + AddlDataRequest + "]";
	}
}
