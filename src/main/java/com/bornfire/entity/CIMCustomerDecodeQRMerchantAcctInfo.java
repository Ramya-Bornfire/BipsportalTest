package com.bornfire.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "globalID", "payeeParticipantCode", "customerID" })
public class CIMCustomerDecodeQRMerchantAcctInfo {
	private String GlobalID;

	private String PayeeParticipantCode;

	private String CustomerID;

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

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}
	
	


}
