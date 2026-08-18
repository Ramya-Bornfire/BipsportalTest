package com.bornfire.entity;

import java.util.List;

public class C24FTResponse {

	
	private String status;
	private String error;
	private List<String> error_desc;
	private C24FTResponseBalance Balance;
	private String tranCurrency;
	
	
	public C24FTResponse(String status, C24FTResponseBalance balance, String tranCurrency) {
		super();
		this.status = status;
		Balance = balance;
		this.tranCurrency = tranCurrency;
	}
	
	

	public C24FTResponse() {
		super();
		// TODO Auto-generated constructor stub
	}



	public C24FTResponse(String status, String error, List<String> error_desc) {
		super();
		this.status = status;
		this.error = error;
		this.error_desc = error_desc;
	}



	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<String> getError_desc() {
		return error_desc;
	}

	public void setError_desc(List<String> error_desc) {
		this.error_desc = error_desc;
	}

	public C24FTResponseBalance getBalance() {
		return Balance;
	}

	public void setBalance(C24FTResponseBalance balance) {
		Balance = balance;
	}

	public String getTranCurrency() {
		return tranCurrency;
	}

	public void setTranCurrency(String tranCurrency) {
		this.tranCurrency = tranCurrency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
