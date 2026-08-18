package com.bornfire.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CIMCustomerQRcodeRequest {

	@NotBlank(message="PayloadFormatIndiator Required")
    @Size(min=2,max = 2, message = "PayloadFormatIndiator must contains 2 characters")
	private String payloadFormatIndiator;
	
	@Size(min=2,max = 2, message = "PointOfInitiationFormat must contains 2 characters")
	private String pointOfInitiationFormat;
	
	@NotNull(message="CustomerAccountInformation Required")
	@Valid
	private CIMCustomerQRcodeAcctInfo payeeAccountInformation;

	
	@NotBlank(message="Currency Required")
	@Size(min=3,max = 3, message = "Currency should contains 3 characters")
	private String currency;
	@JsonProperty("convenienceIndicator")
	private String ConvenienceIndicator;
	
	@NotBlank(message="CountryCode Required")
	@Size(max = 2, message = "CountryCode should not exceed 2 characters")
	private String countryCode;
	
	
	@NotBlank(message="CustomerName Required")
	@Size(max = 25, message = "CustomerName should not exceed 25 characters")
	private String customerName;
	
	@NotBlank(message="City Required")
	@Size(max = 15, message = "City should not exceed 15 characters")
	private String city;
	
	private CIMCustomerQRAddlInfo additionalDataInformation;

	public String getPayloadFormatIndiator() {
		return payloadFormatIndiator;
	}

	public void setPayloadFormatIndiator(String payloadFormatIndiator) {
		this.payloadFormatIndiator = payloadFormatIndiator;
	}

	public String getPointOfInitiationFormat() {
		return pointOfInitiationFormat;
	}

	public void setPointOfInitiationFormat(String pointOfInitiationFormat) {
		this.pointOfInitiationFormat = pointOfInitiationFormat;
	}

	public CIMCustomerQRcodeAcctInfo getPayeeAccountInformation() {
		return payeeAccountInformation;
	}

	public void setPayeeAccountInformation(CIMCustomerQRcodeAcctInfo payeeAccountInformation) {
		this.payeeAccountInformation = payeeAccountInformation;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getConvenienceIndicator() {
		return ConvenienceIndicator;
	}

	public void setConvenienceIndicator(String convenienceIndicator) {
		ConvenienceIndicator = convenienceIndicator;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public CIMCustomerQRAddlInfo getAdditionalDataInformation() {
		return additionalDataInformation;
	}

	public void setAdditionalDataInformation(CIMCustomerQRAddlInfo additionalDataInformation) {
		this.additionalDataInformation = additionalDataInformation;
	}

	public CIMCustomerQRcodeRequest(
			@NotBlank(message = "PayloadFormatIndiator Required") @Size(min = 2, max = 2, message = "PayloadFormatIndiator must contains 2 characters") String payloadFormatIndiator,
			@Size(min = 2, max = 2, message = "PointOfInitiationFormat must contains 2 characters") String pointOfInitiationFormat,
			@NotNull(message = "CustomerAccountInformation Required") @Valid CIMCustomerQRcodeAcctInfo payeeAccountInformation,
			@NotBlank(message = "Currency Required") @Size(min = 3, max = 3, message = "Currency should contains 3 characters") String currency,
			String convenienceIndicator,
			@NotBlank(message = "CountryCode Required") @Size(max = 2, message = "CountryCode should not exceed 2 characters") String countryCode,
			@NotBlank(message = "CustomerName Required") @Size(max = 25, message = "CustomerName should not exceed 25 characters") String customerName,
			@NotBlank(message = "City Required") @Size(max = 15, message = "City should not exceed 15 characters") String city,
			CIMCustomerQRAddlInfo additionalDataInformation) {
		super();
		this.payloadFormatIndiator = payloadFormatIndiator;
		this.pointOfInitiationFormat = pointOfInitiationFormat;
		this.payeeAccountInformation = payeeAccountInformation;
		this.currency = currency;
		ConvenienceIndicator = convenienceIndicator;
		this.countryCode = countryCode;
		this.customerName = customerName;
		this.city = city;
		this.additionalDataInformation = additionalDataInformation;
	}

	public CIMCustomerQRcodeRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CIMCustomerQRcodeRequest [payloadFormatIndiator=" + payloadFormatIndiator + ", pointOfInitiationFormat="
				+ pointOfInitiationFormat + ", payeeAccountInformation=" + payeeAccountInformation + ", currency="
				+ currency + ", ConvenienceIndicator=" + ConvenienceIndicator + ", countryCode=" + countryCode
				+ ", customerName=" + customerName + ", city=" + city + ", additionalDataInformation="
				+ additionalDataInformation + "]";
	}
	
	


}
