package com.bornfire.entity;

import javax.validation.constraints.NotBlank;

public class CIMToAccount {
	
	@NotBlank(message="To Account Name Required")
	private String AcctName;
	
	@NotBlank(message="To Account Number Required")
	private String AcctNumber;
	
	@NotBlank(message="Bank Code Required")
	private String BankCode;
	
	public CIMToAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CIMToAccount(String acctName, String acctNumber, String bankCode) {
		super();
		AcctName = acctName;
		AcctNumber = acctNumber;
		BankCode = bankCode;
	}
	public String getAcctName() {
		return AcctName;
	}
	public void setAcctName(String acctName) {
		AcctName = acctName;
	}
	public String getAcctNumber() {
		return AcctNumber;
	}
	public void setAcctNumber(String acctNumber) {
		AcctNumber = acctNumber;
	}
	public String getBankCode() {
		return BankCode;
	}
	public void setBankCode(String bankCode) {
		BankCode = bankCode;
	}
	
	
	
	
}
