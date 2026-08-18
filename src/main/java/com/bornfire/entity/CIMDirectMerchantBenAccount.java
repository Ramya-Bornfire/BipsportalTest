package com.bornfire.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bornfire.exception.TranAmount;

public class CIMDirectMerchantBenAccount {
	@NotBlank(message="GloballyUniqueIdentifier Required")
    @Size(max = 11, message = "GloballyUniqueIdentifier should not exceed 11 characters")
	private String GlobalID;
	
    @Size(min=2,max = 2, message = "PointOfInitiationFormat must contains 2 characters")
	private String PointOfInitiationFormat;
	
	@NotBlank(message="MerchantName Required")
	@Size(max = 25, message = "MerchantName should not exceed 25 characters")
	private String MerchantName;
	
	@NotBlank(message="MerchantAcctNumber Required")
	@Size(max = 38, message = "MerchantAcctNumber should not exceed 38 characters")
	private String MerchantAcctNumber;
	
	@NotBlank(message="Merchant ID Required")
	@Size(max = 15, message = "Merchant ID should not exceed 15 characters")
	private String MerchantID;
	
	@NotBlank(message="PayeeParticipantCode Required")
	@Size(min=8,max = 12, message = "PayeeParticipantCode should not exceed 12 characters")
	private String PayeeParticipantCode;
	
	@NotBlank(message="MerchantCategoryCode Required")
	@Size(min=4,max = 4, message = "MerchantCategoryCode should contains 4 characters")
	private String MCC;
	
	@NotBlank(message="Currency Required")
	@Size(min=3,max = 3, message = "Currency should contains 3 characters")
	private String Currency;
	
	@NotBlank(message="TransactionAmount Required")
	@TranAmount
	private String TrAmt;
	
	
	/*@NotNull(message="ConvenienceIndicator required(true or false)")
	private boolean ConvenienceIndicator;
	
	
	private String ConvenienceIndicatorFeeType;*/
	
	private String TipOrConvenienceIndicator;
    
	private String ConvenienceIndicatorFee;
	
	private String TipAmt;
	
	@NotBlank(message="City Required")
	@Size(max = 15, message = "City should not exceed 15 characters")
	private String City;
	
	@Size(max = 10, message = "PostalCode should not exceed 10 characters")
	private String PostalCode;
	
	@NotBlank(message="CountryCode Required")
	@Size(max = 2, message = "CountryCode should not exceed 2 characters")
	private String CountryCode;
	
	
	public String getGlobalID() {
		return GlobalID;
	}
	public void setGlobalID(String globalID) {
		GlobalID = globalID;
	}
	public String getPointOfInitiationFormat() {
		return PointOfInitiationFormat;
	}
	public void setPointOfInitiationFormat(String pointOfInitiationFormat) {
		PointOfInitiationFormat = pointOfInitiationFormat;
	}
	public String getMerchantName() {
		return MerchantName;
	}
	public void setMerchantName(String merchantName) {
		MerchantName = merchantName;
	}
	public String getMerchantAcctNumber() {
		return MerchantAcctNumber;
	}
	public void setMerchantAcctNumber(String merchantAcctNumber) {
		MerchantAcctNumber = merchantAcctNumber;
	}
	public String getMerchantID() {
		return MerchantID;
	}
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}
	public String getPayeeParticipantCode() {
		return PayeeParticipantCode;
	}
	public void setPayeeParticipantCode(String payeeParticipantCode) {
		PayeeParticipantCode = payeeParticipantCode;
	}
	public String getMCC() {
		return MCC;
	}
	public void setMCC(String mCC) {
		MCC = mCC;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getTrAmt() {
		return TrAmt;
	}
	public void setTrAmt(String trAmt) {
		TrAmt = trAmt;
	}
	/*public boolean isConvenienceIndicator() {
		return ConvenienceIndicator;
	}
	public void setConvenienceIndicator(boolean convenienceIndicator) {
		ConvenienceIndicator = convenienceIndicator;
	}
	public String getConvenienceIndicatorFeeType() {
		return ConvenienceIndicatorFeeType;
	}
	public void setConvenienceIndicatorFeeType(String convenienceIndicatorFeeType) {
		ConvenienceIndicatorFeeType = convenienceIndicatorFeeType;
	}*/
	
	
	public String getConvenienceIndicatorFee() {
		return ConvenienceIndicatorFee;
	}
	public String getTipOrConvenienceIndicator() {
		return TipOrConvenienceIndicator;
	}
	public void setTipOrConvenienceIndicator(String tipOrConvenienceIndicator) {
		TipOrConvenienceIndicator = tipOrConvenienceIndicator;
	}
	
	
	public String getTipAmt() {
		return TipAmt;
	}
	public void setTipAmt(String tipAmt) {
		TipAmt = tipAmt;
	}
	public void setConvenienceIndicatorFee(String convenienceIndicatorFee) {
		ConvenienceIndicatorFee = convenienceIndicatorFee;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getPostalCode() {
		return PostalCode;
	}
	public void setPostalCode(String postalCode) {
		PostalCode = postalCode;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
		
	
		
	
}
