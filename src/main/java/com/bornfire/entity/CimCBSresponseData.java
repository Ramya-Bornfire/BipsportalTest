package com.bornfire.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "transactionNo", "transactionNoFromCBS", "transactionDate" })
public class CimCBSresponseData {
	
	@JsonProperty("transactionNo")
	private String transactionNo;
	
	@JsonProperty("transactionNoFromCBS")
	private String transactionNoFromCBS;
	
	@JsonProperty("transactionDate")
	private Date transactionDate;

	@JsonProperty("transactionNo")
	public String getTransactionNo() {
		return transactionNo;
	}

	@JsonProperty("transactionNo")
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	@JsonProperty("transactionNoFromCBS")
	public String getTransactionNoFromCBS() {
		return transactionNoFromCBS;
	}

	@JsonProperty("transactionNoFromCBS")
	public void setTransactionNoFromCBS(String transactionNoFromCBS) {
		this.transactionNoFromCBS = transactionNoFromCBS;
	}

	@JsonProperty("transactionDate")
	public Date getTransactionDate() {
		return transactionDate;
	}

	@JsonProperty("transactionDate")
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	@Override
	public String toString() {
		return "CimCBSresponseData [transactionNo=" + transactionNo + ", transactionNoFromCBS=" + transactionNoFromCBS
				+ ", transactionDate=" + transactionDate + "]";
	}
	
	
}
