package com.bornfire.entity;

public class C24RequestAcount {
	private String SchmType;
	private String AcctNumber;
	private String SettlAcctNumber;
	private String benAcctName;

	public C24RequestAcount() {
		super();
	}

	public C24RequestAcount(String schmType, String acctNumber, String settlAcctNumber, String benAcctName) {
		super();
		SchmType = schmType;
		AcctNumber = acctNumber;
		SettlAcctNumber = settlAcctNumber;
		this.benAcctName = benAcctName;
	}

	public String getBenAcctName() {
		return benAcctName;
	}

	public void setBenAcctName(String benAcctName) {
		this.benAcctName = benAcctName;
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

	public String getSettlAcctNumber() {
		return SettlAcctNumber;
	}

	public void setSettlAcctNumber(String settlAcctNumber) {
		SettlAcctNumber = settlAcctNumber;
	}
}
