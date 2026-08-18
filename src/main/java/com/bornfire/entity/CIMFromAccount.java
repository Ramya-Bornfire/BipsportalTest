package com.bornfire.entity;

import javax.validation.constraints.NotBlank;

public class CIMFromAccount {

	private String SchmType;
	
	@NotBlank(message="From Account Name Required")
	private String AcctName;
	
	@NotBlank(message="From Account Number Required")
	private String AcctNumber;
	

	public CIMFromAccount(String schmType, String acctName, String acctNumber) {
		super();
		SchmType = schmType;
		AcctName = acctName;
		AcctNumber = acctNumber;
	}



	public CIMFromAccount() {
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

	
}
