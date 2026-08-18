package com.bornfire.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CIMMerchantQRcodeRequest {

	@NotBlank(message="PayloadFormatIndiator Required")
    @Size(min=2,max = 2, message = "PayloadFormatIndiator must contains 2 characters")
	private String PayloadFormatIndiator;
	
	@Size(min=2,max = 2, message = "PointOfInitiationFormat must contains 2 characters")
	private String PointOfInitiationFormat;
	
	@NotNull(message="MerchantAccountInformation Required")
	@Valid
	private CIMMerchantQRcodeAcctInfo merchantAcctInformation;

	
	//@NotBlank(message="MerchantCategoryCode Required")
	@Size(min=4,max = 4, message = "MerchantCategoryCode should contains 4 characters")
	private String MCC;
	
	@NotBlank(message="Currency Required")
	@Size(min=3,max = 3, message = "Currency should contains 3 characters")
	private String Currency;
	
	//@NotBlank(message="Transaction Amount Required")
	//@TranAmount
	private String TrAmt;
	
	
	//@NotNull(message="ConvenienceIndicator required(true or false)")
	private String TipOrConvenienceIndicator;
	
	
	//private String ConvenienceIndicatorFeeType;
    
	private String ConvenienceIndicatorFee;
	
	@NotBlank(message="CountryCode Required")
	@Size(max = 2, message = "CountryCode should not exceed 2 characters")
	private String CountryCode;
	
	
	@NotBlank(message="MerchantName Required")
	@Size(max = 25, message = "MerchantName should not exceed 25 characters")
	private String MerchantName;
	
	@NotBlank(message="City Required")
	@Size(max = 15, message = "City should not exceed 15 characters")
	private String City;
	
	@Size(max = 10, message = "PostalCode should not exceed 10 characters")
	private String PostalCode;
	
	private CIMMerchantQRAddlInfo AdditionalDataInformation;

	public String getPayloadFormatIndiator() {
		return PayloadFormatIndiator;
	}

	public void setPayloadFormatIndiator(String payloadFormatIndiator) {
		PayloadFormatIndiator = payloadFormatIndiator;
	}

	public String getPointOfInitiationFormat() {
		return PointOfInitiationFormat;
	}

	public void setPointOfInitiationFormat(String pointOfInitiationFormat) {
		PointOfInitiationFormat = pointOfInitiationFormat;
	}

	public CIMMerchantQRcodeAcctInfo getMerchantAcctInformation() {
		return merchantAcctInformation;
	}

	public void setMerchantAcctInformation(CIMMerchantQRcodeAcctInfo merchantAcctInformation) {
		this.merchantAcctInformation = merchantAcctInformation;
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

	public void setConvenienceIndicatorFee(String convenienceIndicatorFee) {
		ConvenienceIndicatorFee = convenienceIndicatorFee;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}

	public String getMerchantName() {
		return MerchantName;
	}

	public void setMerchantName(String merchantName) {
		MerchantName = merchantName;
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

	public CIMMerchantQRAddlInfo getAdditionalDataInformation() {
		return AdditionalDataInformation;
	}

	public void setAdditionalDataInformation(CIMMerchantQRAddlInfo additionalDataInformation) {
		AdditionalDataInformation = additionalDataInformation;
	}

	@Override
	public String toString() {
		return "CIMMerchantQRcodeRequest [PayloadFormatIndiator=" + PayloadFormatIndiator + ", PointOfInitiationFormat="
				+ PointOfInitiationFormat + ", merchantAcctInformation=" + merchantAcctInformation + ", MCC=" + MCC
				+ ", Currency=" + Currency + ", TrAmt=" + TrAmt + ", TipOrConvenienceIndicator="
				+ TipOrConvenienceIndicator + ", ConvenienceIndicatorFee=" + ConvenienceIndicatorFee + ", CountryCode="
				+ CountryCode + ", MerchantName=" + MerchantName + ", City=" + City + ", PostalCode=" + PostalCode
				+ ", AdditionalDataInformation=" + AdditionalDataInformation + "]";
	}

}
