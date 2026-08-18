package com.bornfire.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CimUpdatePaymentStatusRequest {

	
	@JsonProperty("transactionNo")
	private String transactionNo;
	
	@JsonProperty("tranId")
	private String tranId;
	
	@JsonProperty("transactionDate")
	private String transactionDate;
	
	@JsonProperty("referenceId")
	private String referenceId;
	
	@JsonProperty("toAccountNumber")
	private String toAccountNumber;
	
	@JsonProperty("transactionAmount")
	private BigDecimal transactionAmount;
	
	@JsonProperty("receiptNumber")
	private String receiptNumber;
	
	@JsonProperty("isSuccess")
	private Boolean isSuccess;
	
	@JsonProperty("statusCode")
	private String statusCode;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("productType")
	private String productType;

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getTranId() {
		return tranId;
	}

	public void setTranId(String tranId) {
		this.tranId = tranId;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getToAccountNumber() {
		return toAccountNumber;
	}

	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "CimUpdatePaymentStatusRequest [transactionNo=" + transactionNo + ", tranId=" + tranId
				+ ", transactionDate=" + transactionDate + ", referenceId=" + referenceId + ", toAccountNumber="
				+ toAccountNumber + ", transactionAmount=" + transactionAmount + ", receiptNumber=" + receiptNumber
				+ ", isSuccess=" + isSuccess + ", statusCode=" + statusCode + ", message=" + message + ", productType="
				+ productType + "]";
	}
	
	
	
	
	
	
}
