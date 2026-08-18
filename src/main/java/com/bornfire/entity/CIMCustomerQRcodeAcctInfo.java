package com.bornfire.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CIMCustomerQRcodeAcctInfo {
	
	@NotBlank(message="GloballyUniqueIdentifier Required")
    @Size(max =11, message = "GloballyUniqueIdentifier shouls not exceeds 11 characters")
	private String globalID;

	@NotBlank(message="PayeeParticipantCode Required")
    @Size(min=8,max =12, message = "PayeeParticipantCode must contains 12 characters")
	private String payeeParticipantCode;

	@NotBlank(message="CustomerID Required")
    @Size(max =15, message = "CustomerID should not exceeds 15 characters")
	private String customerID;

	public String getGlobalID() {
		return globalID;
	}

	public void setGlobalID(String globalID) {
		this.globalID = globalID;
	}

	public String getPayeeParticipantCode() {
		return payeeParticipantCode;
	}

	public void setPayeeParticipantCode(String payeeParticipantCode) {
		this.payeeParticipantCode = payeeParticipantCode;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public CIMCustomerQRcodeAcctInfo(
			@NotBlank(message = "GloballyUniqueIdentifier Required") @Size(max = 11, message = "GloballyUniqueIdentifier shouls not exceeds 11 characters") String globalID,
			@NotBlank(message = "PayeeParticipantCode Required") @Size(min = 8, max = 12, message = "PayeeParticipantCode must contains 12 characters") String payeeParticipantCode,
			@NotBlank(message = "CustomerID Required") @Size(max = 15, message = "CustomerID should not exceeds 15 characters") String customerID) {
		super();
		this.globalID = globalID;
		this.payeeParticipantCode = payeeParticipantCode;
		this.customerID = customerID;
	}

	public CIMCustomerQRcodeAcctInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CIMCustomerQRcodeAcctInfo [globalID=" + globalID + ", payeeParticipantCode=" + payeeParticipantCode
				+ ", customerID=" + customerID + "]";
	}


	


}
