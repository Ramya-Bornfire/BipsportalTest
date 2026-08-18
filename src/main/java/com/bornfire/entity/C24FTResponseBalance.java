package com.bornfire.entity;

/****Response Pojo for Connect24 FundTransfer*****/
/****Connect24Response Balance*****/

public class C24FTResponseBalance {
	private String LedgeBalance;
	private String AvailableBalance;
	private String FloatBalance;
	private String FFTBalance;
	private String UserDefBalance;
	
	
	public C24FTResponseBalance() {
		super();
		// TODO Auto-generated constructor stub
	}


	public C24FTResponseBalance(String ledgeBalance, String availableBalance, String floatBalance, String fFTBalance,
			String userDefBalance) {
		super();
		LedgeBalance = ledgeBalance;
		AvailableBalance = availableBalance;
		FloatBalance = floatBalance;
		FFTBalance = fFTBalance;
		UserDefBalance = userDefBalance;
	}


	public String getLedgeBalance() {
		return LedgeBalance;
	}


	public void setLedgeBalance(String ledgeBalance) {
		LedgeBalance = ledgeBalance;
	}


	public String getAvailableBalance() {
		return AvailableBalance;
	}


	public void setAvailableBalance(String availableBalance) {
		AvailableBalance = availableBalance;
	}


	public String getFloatBalance() {
		return FloatBalance;
	}


	public void setFloatBalance(String floatBalance) {
		FloatBalance = floatBalance;
	}


	public String getFFTBalance() {
		return FFTBalance;
	}


	public void setFFTBalance(String fFTBalance) {
		FFTBalance = fFTBalance;
	}


	public String getUserDefBalance() {
		return UserDefBalance;
	}


	public void setUserDefBalance(String userDefBalance) {
		UserDefBalance = userDefBalance;
	}

}
