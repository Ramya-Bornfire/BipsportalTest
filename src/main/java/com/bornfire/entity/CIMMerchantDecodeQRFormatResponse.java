package com.bornfire.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"payloadFormatIndiator","pointOfInitiationFormat","merchantAcctInformation","mcc","currency",
	"trAmt","tipOrConvenienceIndicator","convenienceIndicatorFee",
	"countryCode","merchantName","city","postalCode","additionalDataInformation"})
public class CIMMerchantDecodeQRFormatResponse {

	private String PayloadFormatIndiator;
		
	private String PointOfInitiationFormat;
	
	private CIMMerchantDecodeQRMerchantAcctInfo merchantAcctInformation;
	
	private String MCC;
	
	private String Currency;
	
	private String TrAmt;

    private String TipOrConvenienceIndicator;
	
	//private String ConvenienceIndicatorFeeType;
    
	private String ConvenienceIndicatorFee;
	
	private String CountryCode;

	private String MerchantName;
	
	private String City;
	
	private String PostalCode;
	
	private CIMMerchantDecodeQRMerchantAddlInfo AdditionalDataInformation;

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

	public CIMMerchantDecodeQRMerchantAcctInfo getMerchantAcctInformation() {
		return merchantAcctInformation;
	}

	public void setMerchantAcctInformation(CIMMerchantDecodeQRMerchantAcctInfo merchantAcctInformation) {
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
	}
*/
	
	
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

	public CIMMerchantDecodeQRMerchantAddlInfo getAdditionalDataInformation() {
		return AdditionalDataInformation;
	}

	public void setAdditionalDataInformation(CIMMerchantDecodeQRMerchantAddlInfo additionalDataInformation) {
		AdditionalDataInformation = additionalDataInformation;
	} 
	
	

}
