package com.bornfire.entity;

import javax.validation.constraints.NotBlank;

@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class CIMDirectMerchantRemitterAccount {
private String SchmType;
	
	@NotBlank(message="Remitter Account Name Required")
	private String AcctName;
	
	@NotBlank(message="Remitter Account Number Required")
	private String AcctNumber;
	
	//@NotBlank(message="Remitter Bank Code Required")
	private String BankCode;
	
//	@NotBlank(message="Remitter Currency Code Required")
	private String CurrencyCode;
	

	public CIMDirectMerchantRemitterAccount(String schmType, String acctName, String acctNumber) {
		super();
		SchmType = schmType;
		AcctName = acctName;
		AcctNumber = acctNumber;
	}



	public CIMDirectMerchantRemitterAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public String toString() {
		return "FrAccount [SchmType=" + SchmType + ", AcctNumber=" + AcctNumber + "]";
	}

	

	public String getSchmType() {
		return SchmType;
	}



	public void setSchmType(String schmType) {
		SchmType = schmType;
	}



	public String getAcctNumber() {
		return AcctNumber;
	}

	public void setAcctNumber(String acctNumber) {
		AcctNumber = acctNumber;
	}

	public String getAcctName() {
		return AcctName;
	}

	public void setAcctName(String acctName) {
		AcctName = acctName;
	}



	public String getBankCode() {
		return BankCode;
	}



	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}



	public String getCurrencyCode() {
		return CurrencyCode;
	}



	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}

}
