package com.bornfire.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CimCBSrequestData {
	
	@JsonProperty("branchId")
	private String branchId;
	
	@JsonProperty("transactionNo")
	private String transactionNo;
	
	@JsonProperty("initiatingChannel")
	private String initiatingChannel;
	
	@JsonProperty("initatorTransactionNo")
	private String initatorTransactionNo;
	
	@JsonProperty("initatorSubTransactionNo")
	private String initatorSubTransactionNo;
	
	@JsonProperty("postToCBS")
	private Boolean PostToCBS;
	
	@JsonProperty("transactionType")
	private String transactionType;
	
	@JsonProperty("isReversal")
	private String isReversal;
	
	@JsonProperty("transactionNoFromCBS")
	private String transactionNoFromCBS;
	
	@JsonProperty("customerName")
	private String customerName;
	
	@JsonProperty("fromAccountNo")
	private String fromAccountNo;
	
	@JsonProperty("toAccountNo")
	private String toAccountNo;
	
	@JsonProperty("transactionAmount")
	private BigDecimal transactionAmount;
	
	//@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	@JsonProperty("transactionDate")
	private String transactionDate;
	
	@JsonProperty("transactionCurrency")
	private String transactionCurrency;
	
	@JsonProperty("transactionParticularCode")
	private String transactionParticularCode;
	
	@JsonProperty("creditRemarks")
	private String creditRemarks;
	
	@JsonProperty("debitRemarks")
	private String debitRemarks;
	
	@JsonProperty("reservedField1")
	private String reservedField1;
	
	@JsonProperty("reservedField2")
	private String reservedField2;
	
	@JsonProperty("errorCode")
	private String errorCode;
	
	@JsonProperty("errorMessage")
	private String errorMessage;
	
	@JsonProperty("ipsMasterRefId")
	private String ipsMasterRefId;
	
	@JsonProperty("remitterBank")
	private String remitterBank;
	
	@JsonProperty("remitterBankCode")
	private String remitterBankCode;
	
	@JsonProperty("remitterSwiftCode")
	private String remitterSwiftCode;
	
	@JsonProperty("beneficiaryBank")
	private String beneficiaryBank;
	
	@JsonProperty("beneficiaryBankCode")
	private String beneficiaryBankCode;
	
	@JsonProperty("beneficiarySwiftCode")
	private String beneficiarySwiftCode;
	
	@JsonProperty("ipsxTranStatus")
	private Boolean ipsxTranStatus;
	
	@JsonProperty("transactionNoToCBS")
	private String transactionNoToCBS;
	
	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getInitiatingChannel() {
		return initiatingChannel;
	}

	public void setInitiatingChannel(String initiatingChannel) {
		this.initiatingChannel = initiatingChannel;
	}

	public String getInitatorTransactionNo() {
		return initatorTransactionNo;
	}

	public void setInitatorTransactionNo(String initatorTransactionNo) {
		this.initatorTransactionNo = initatorTransactionNo;
	}

	public Boolean getPostToCBS() {
		return PostToCBS;
	}

	public void setPostToCBS(Boolean postToCBS) {
		PostToCBS = postToCBS;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getIsReversal() {
		return isReversal;
	}

	public void setIsReversal(String isReversal) {
		this.isReversal = isReversal;
	}

	public String getTransactionNoFromCBS() {
		return transactionNoFromCBS;
	}

	public void setTransactionNoFromCBS(String transactionNoFromCBS) {
		this.transactionNoFromCBS = transactionNoFromCBS;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(String fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public String getToAccountNo() {
		return toAccountNo;
	}

	public void setToAccountNo(String toAccountNo) {
		this.toAccountNo = toAccountNo;
	}

	public BigDecimal getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(BigDecimal transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionCurrency() {
		return transactionCurrency;
	}

	public void setTransactionCurrency(String transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}

	public String getTransactionParticularCode() {
		return transactionParticularCode;
	}

	public void setTransactionParticularCode(String transactionParticularCode) {
		this.transactionParticularCode = transactionParticularCode;
	}

	public String getCreditRemarks() {
		return creditRemarks;
	}

	public void setCreditRemarks(String creditRemarks) {
		this.creditRemarks = creditRemarks;
	}

	public String getDebitRemarks() {
		return debitRemarks;
	}

	public void setDebitRemarks(String debitRemarks) {
		this.debitRemarks = debitRemarks;
	}

	public String getReservedField1() {
		return reservedField1;
	}

	public void setReservedField1(String reservedField1) {
		this.reservedField1 = reservedField1;
	}

	public String getReservedField2() {
		return reservedField2;
	}

	public void setReservedField2(String reservedField2) {
		this.reservedField2 = reservedField2;
	}
	
	

	public String getInitatorSubTransactionNo() {
		return initatorSubTransactionNo;
	}

	public void setInitatorSubTransactionNo(String initatorSubTransactionNo) {
		this.initatorSubTransactionNo = initatorSubTransactionNo;
	}

	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getIpsMasterRefId() {
		return ipsMasterRefId;
	}

	public void setIpsMasterRefId(String ipsMasterRefId) {
		this.ipsMasterRefId = ipsMasterRefId;
	}
	
	

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getRemitterBank() {
		return remitterBank;
	}

	public void setRemitterBank(String remitterBank) {
		this.remitterBank = remitterBank;
	}

	public String getRemitterBankCode() {
		return remitterBankCode;
	}

	public void setRemitterBankCode(String remitterBankCode) {
		this.remitterBankCode = remitterBankCode;
	}

	public String getRemitterSwiftCode() {
		return remitterSwiftCode;
	}

	public void setRemitterSwiftCode(String remitterSwiftCode) {
		this.remitterSwiftCode = remitterSwiftCode;
	}

	public String getBeneficiaryBank() {
		return beneficiaryBank;
	}

	public void setBeneficiaryBank(String beneficiaryBank) {
		this.beneficiaryBank = beneficiaryBank;
	}

	public String getBeneficiaryBankCode() {
		return beneficiaryBankCode;
	}

	public void setBeneficiaryBankCode(String beneficiaryBankCode) {
		this.beneficiaryBankCode = beneficiaryBankCode;
	}

	public String getBeneficiarySwiftCode() {
		return beneficiarySwiftCode;
	}

	public void setBeneficiarySwiftCode(String beneficiarySwiftCode) {
		this.beneficiarySwiftCode = beneficiarySwiftCode;
	}

	public Boolean getIpsxTranStatus() {
		return ipsxTranStatus;
	}

	public void setIpsxTranStatus(Boolean ipsxTranStatus) {
		this.ipsxTranStatus = ipsxTranStatus;
	}

	public String getTransactionNoToCBS() {
		return transactionNoToCBS;
	}

	public void setTransactionNoToCBS(String transactionNoToCBS) {
		this.transactionNoToCBS = transactionNoToCBS;
	}

	@Override
	public String toString() {
		return "CimCBSrequestData [branchId=" + branchId + ", transactionNo=" + transactionNo + ", initiatingChannel="
				+ initiatingChannel + ", initatorTransactionNo=" + initatorTransactionNo + ", initatorSubTransactionNo="
				+ initatorSubTransactionNo + ", PostToCBS=" + PostToCBS + ", transactionType=" + transactionType
				+ ", isReversal=" + isReversal + ", transactionNoFromCBS=" + transactionNoFromCBS + ", customerName="
				+ customerName + ", fromAccountNo=" + fromAccountNo + ", toAccountNo=" + toAccountNo
				+ ", transactionAmount=" + transactionAmount + ", transactionDate=" + transactionDate
				+ ", transactionCurrency=" + transactionCurrency + ", transactionParticularCode="
				+ transactionParticularCode + ", creditRemarks=" + creditRemarks + ", debitRemarks=" + debitRemarks
				+ ", reservedField1=" + reservedField1 + ", reservedField2=" + reservedField2 + ", errorCode="
				+ errorCode + ", errorMessage=" + errorMessage + ", ipsMasterRefId=" + ipsMasterRefId
				+ ", remitterBank=" + remitterBank + ", remitterBankCode=" + remitterBankCode + ", remitterSwiftCode="
				+ remitterSwiftCode + ", beneficiaryBank=" + beneficiaryBank + ", beneficiaryBankCode="
				+ beneficiaryBankCode + ", beneficiarySwiftCode=" + beneficiarySwiftCode + ", ipsxTranStatus="
				+ ipsxTranStatus + ", transactionNoToCBS=" + transactionNoToCBS + "]";
	}









	
	

	
}
