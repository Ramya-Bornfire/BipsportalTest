package com.bornfire.entity;

public class C24FTRequest {

	private C24RequestAcount account;
	private String Currency_Code;
	private String TrAmt;
	private String PAN;
	private String TrRmks;
	private String ParentRRN;

	public C24FTRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public C24RequestAcount getAccount() {
		return account;
	}

	public void setAccount(C24RequestAcount account) {
		this.account = account;
	}

	public String getCurrency_Code() {
		return Currency_Code;
	}

	public void setCurrency_Code(String currency_Code) {
		Currency_Code = currency_Code;
	}

	public String getTrAmt() {
		return TrAmt;
	}

	public void setTrAmt(String trAmt) {
		TrAmt = trAmt;
	}

	public String getPAN() {
		return PAN;
	}

	public void setPAN(String pAN) {
		PAN = pAN;
	}

	public String getTrRmks() {
		return TrRmks;
	}

	public void setTrRmks(String trRmks) {
		TrRmks = trRmks;
	}

	public String getParentRRN() {
		return ParentRRN;
	}

	public void setParentRRN(String parentRRN) {
		ParentRRN = parentRRN;
	}

	public C24FTRequest(C24RequestAcount account, String currency_Code, String trAmt, String pAN, String trRmks,
			String parentRRN) {
		super();
		this.account = account;
		Currency_Code = currency_Code;
		TrAmt = trAmt;
		PAN = pAN;
		TrRmks = trRmks;
		ParentRRN = parentRRN;
	}

}
