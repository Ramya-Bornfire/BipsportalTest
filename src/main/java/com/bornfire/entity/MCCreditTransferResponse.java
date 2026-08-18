package com.bornfire.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonInclude(JsonInclude.Include.NON_NULL)

public class MCCreditTransferResponse {
	private String TranID;
	private String TranDateTime;
	private String Status;
	private C24FTResponseBalance Balance;
	
	public MCCreditTransferResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MCCreditTransferResponse(String tranID, String tranDateTime) {
		super();
		TranID = tranID;
		TranDateTime = tranDateTime;
		
	}

	public MCCreditTransferResponse(String tranID, String tranDateTime, String status,
			C24FTResponseBalance balance) {
		super();
		TranID = tranID;
		TranDateTime = tranDateTime;
		Status = status;
		Balance = balance;
	}





	public String getTranID() {
		return TranID;
	}


	public void setTranID(String tranID) {
		TranID = tranID;
	}


	public String getTranDateTime() {
		return TranDateTime;
	}


	public void setTranDateTime(String tranDateTime) {
		TranDateTime = tranDateTime;
	}


	public String getStatus() {
		return Status;
	}


	public void setStatus(String status) {
		Status = status;
	}


	public C24FTResponseBalance getBalance() {
		return Balance;
	}


	public void setBalance(C24FTResponseBalance balance) {
		Balance = balance;
	}





	@Override
	public String toString() {
		return "MCCreditTransferResponse [TranID=" + TranID + ", TranDateTime=" + TranDateTime + ", Status=" + Status
				+ ", Balance=" + Balance + "]";
	}
	
	
}
