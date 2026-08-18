package com.bornfire.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"payloadFormatIndiator","pointOfInitiationFormat","payeeAccountInformation","currency",
	"trAmt","convenienceIndicatorFee",
	"countryCode","customerName","city","additionalDataInformation"})
public class CIMCustomerDecodeQRFormatResponse {

	private String PayloadFormatIndiator;
		
	private String PointOfInitiationFormat;
	
	private CIMCustomerDecodeQRMerchantAcctInfo payeeAccountInformation;
	
	private String Currency;
	
	private String TrAmt;
    
	private String ConvenienceIndicatorFee;
	
	private String CountryCode;

	private String CustomerName;
	
	private String City;
	
	private String PostalCode;
	
	private CIMMerchantDecodeQRCustomerAddlInfo AdditionalDataInformation;

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

	public CIMCustomerDecodeQRMerchantAcctInfo getPayeeAccountInformation() {
		return payeeAccountInformation;
	}

	public void setPayeeAccountInformation(CIMCustomerDecodeQRMerchantAcctInfo payeeAccountInformation) {
		this.payeeAccountInformation = payeeAccountInformation;
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

	public String getConvenienceIndicatorFee() {
		return ConvenienceIndicatorFee;
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

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
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

	public CIMMerchantDecodeQRCustomerAddlInfo getAdditionalDataInformation() {
		return AdditionalDataInformation;
	}

	public void setAdditionalDataInformation(CIMMerchantDecodeQRCustomerAddlInfo additionalDataInformation) {
		AdditionalDataInformation = additionalDataInformation;
	}

	

}
