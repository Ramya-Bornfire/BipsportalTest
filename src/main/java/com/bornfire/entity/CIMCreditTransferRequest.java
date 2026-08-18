package com.bornfire.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.bornfire.exception.TranAmount;

public class CIMCreditTransferRequest {
	
	@NotNull(message="From Account Details Required")
	@Valid
	private CIMFromAccount FrAccount;
	
	@NotNull(message="To Account Details Required")
	@Valid
	private CIMToAccount ToAccount;
	
	@NotBlank(message="Currency Code Required")
	private String CurrencyCode;
	
	private String Pan;
	
	@NotBlank(message="Transaction Amount Required")
	@TranAmount
	private String TrAmt;
	
	private String TrRmks;
	
	@NotBlank(message="Purpose Code Required")
	private String Purpose;
	
	public CIMCreditTransferRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CIMCreditTransferRequest(CIMFromAccount frAccount, CIMToAccount toAccount, String currencyCode, String pan,
			String trAmt, String trRmks, String purpose) {
		super();
		FrAccount = frAccount;
		ToAccount = toAccount;
		CurrencyCode = currencyCode;
		Pan = pan;
		TrAmt = trAmt;
		TrRmks = trRmks;
		Purpose = purpose;
	}
	
	public CIMFromAccount getFrAccount() {
		return FrAccount;
	}
	public void setFrAccount(CIMFromAccount frAccount) {
		FrAccount = frAccount;
	}
	public CIMToAccount getToAccount() {
		return ToAccount;
	}
	public void setToAccount(CIMToAccount toAccount) {
		ToAccount = toAccount;
	}
	public String getCurrencyCode() {
		return CurrencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}
	public String getPan() {
		return Pan;
	}
	public void setPan(String pan) {
		Pan = pan;
	}
	public String getTrAmt() {
		return TrAmt;
	}
	public void setTrAmt(String trAmt) {
		TrAmt = trAmt;
	}
	public String getTrRmks() {
		return TrRmks;
	}
	public void setTrRmks(String trRmks) {
		TrRmks = trRmks;
	}
	public String getPurpose() {
		return Purpose;
	}
	public void setPurpose(String purpose) {
		Purpose = purpose;
	}

	
}
