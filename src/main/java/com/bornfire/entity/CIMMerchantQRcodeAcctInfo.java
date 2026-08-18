package com.bornfire.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CIMMerchantQRcodeAcctInfo {
	
	@NotBlank(message="GloballyUniqueIdentifier Required")
    @Size(max =11, message = "GloballyUniqueIdentifier shouls not exceeds 11 characters")
	private String GlobalID;

	@NotBlank(message="PayeeParticipantCode Required")
    @Size(min=8,max =12, message = "PayeeParticipantCode must contains 12 characters")
	private String PayeeParticipantCode;

	@NotBlank(message="MerchantAccountNumber Required")
    @Size(max =38, message = "MerchantAccountNumber should not exceeds 38 characters")
	private String MerchantAcctNumber;

	@NotBlank(message="MerchantID Required")
    @Size(max =15, message = "MerchantID should not exceeds 15 characters")
	private String MerchantID;

	public String getGlobalID() {
		return GlobalID;
	}

	public void setGlobalID(String globalID) {
		GlobalID = globalID;
	}

	public String getPayeeParticipantCode() {
		return PayeeParticipantCode;
	}

	public void setPayeeParticipantCode(String payeeParticipantCode) {
		PayeeParticipantCode = payeeParticipantCode;
	}

	public String getMerchantAcctNumber() {
		return MerchantAcctNumber;
	}

	public void setMerchantAcctNumber(String merchantAcctNumber) {
		MerchantAcctNumber = merchantAcctNumber;
	}

	public String getMerchantID() {
		return MerchantID;
	}

	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}

	@Override
	public String toString() {
		return "CIMMerchantQRcodeAcctInfo [GlobalID=" + GlobalID + ", PayeeParticipantCode=" + PayeeParticipantCode
				+ ", MerchantAcctNumber=" + MerchantAcctNumber + ", MerchantID=" + MerchantID + "]";
	}
	
	
}
