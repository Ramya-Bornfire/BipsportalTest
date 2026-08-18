package com.bornfire.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"globalID","payeeParticipantCode","merchantAcctNumber","merchantID"})
public class CIMMerchantDecodeQRMerchantAcctInfo {
	private String GlobalID;

	private String PayeeParticipantCode;

	private String MerchantAcctNumber;

	private String MerchantID;
	
	private String ReserveField;

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

	public String getReserveField() {
		return ReserveField;
	}

	public void setReserveField(String reserveField) {
		ReserveField = reserveField;
	}
	
	
}
