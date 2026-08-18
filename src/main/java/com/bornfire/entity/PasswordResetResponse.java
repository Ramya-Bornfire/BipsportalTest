package com.bornfire.entity;

import java.math.BigDecimal;

public class PasswordResetResponse {
	 private BigDecimal mobileNumber;
	 private BigDecimal otp;
	 private String usercategory;
	public BigDecimal getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(BigDecimal mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public BigDecimal getOtp() {
		return otp;
	}
	public void setOtp(BigDecimal otp) {
		this.otp = otp;
	}
	public String getUsercategory() {
		return usercategory;
	}
	public void setUsercategory(String usercategory) {
		this.usercategory = usercategory;
	}
	public PasswordResetResponse(BigDecimal mobileNumber, BigDecimal otp, String usercategory) {
		super();
		this.mobileNumber = mobileNumber;
		this.otp = otp;
		this.usercategory = usercategory;
	}
	public PasswordResetResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
}
