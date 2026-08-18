package com.bornfire.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BIPS_CUSTOMER_QRCODE_GEN_TABLE")
public class CustomerQRRequest {
	private String	payloadformatindiator;
	private String	pointofinitiationformat;
	private String	globalid;
	private String	payeeparticipantcode;
	@Id
	private String	payeeaccountnumber;
	private String	currency;
	private String	countrycode;
	private String	payeename;
	private String	city;
	private String	billnumber;
	private String	mobilenumber;
	private String	deviceid;
	public String getPayloadformatindiator() {
		return payloadformatindiator;
	}
	public void setPayloadformatindiator(String payloadformatindiator) {
		this.payloadformatindiator = payloadformatindiator;
	}
	public String getPointofinitiationformat() {
		return pointofinitiationformat;
	}
	public void setPointofinitiationformat(String pointofinitiationformat) {
		this.pointofinitiationformat = pointofinitiationformat;
	}
	public String getGlobalid() {
		return globalid;
	}
	public void setGlobalid(String globalid) {
		this.globalid = globalid;
	}
	public String getPayeeparticipantcode() {
		return payeeparticipantcode;
	}
	public void setPayeeparticipantcode(String payeeparticipantcode) {
		this.payeeparticipantcode = payeeparticipantcode;
	}
	public String getPayeeaccountnumber() {
		return payeeaccountnumber;
	}
	public void setPayeeaccountnumber(String payeeaccountnumber) {
		this.payeeaccountnumber = payeeaccountnumber;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	public String getPayeename() {
		return payeename;
	}
	public void setPayeename(String payeename) {
		this.payeename = payeename;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBillnumber() {
		return billnumber;
	}
	public void setBillnumber(String billnumber) {
		this.billnumber = billnumber;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public CustomerQRRequest(String payloadformatindiator, String pointofinitiationformat, String globalid,
			String payeeparticipantcode, String payeeaccountnumber, String currency, String countrycode,
			String payeename, String city, String billnumber, String mobilenumber, String deviceid) {
		super();
		this.payloadformatindiator = payloadformatindiator;
		this.pointofinitiationformat = pointofinitiationformat;
		this.globalid = globalid;
		this.payeeparticipantcode = payeeparticipantcode;
		this.payeeaccountnumber = payeeaccountnumber;
		this.currency = currency;
		this.countrycode = countrycode;
		this.payeename = payeename;
		this.city = city;
		this.billnumber = billnumber;
		this.mobilenumber = mobilenumber;
		this.deviceid = deviceid;
	}
	public CustomerQRRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CustomerQRRequest [payloadformatindiator=" + payloadformatindiator + ", pointofinitiationformat="
				+ pointofinitiationformat + ", globalid=" + globalid + ", payeeparticipantcode=" + payeeparticipantcode
				+ ", payeeaccountnumber=" + payeeaccountnumber + ", currency=" + currency + ", countrycode="
				+ countrycode + ", payeename=" + payeename + ", city=" + city + ", billnumber=" + billnumber
				+ ", mobilenumber=" + mobilenumber + ", deviceid=" + deviceid + "]";
	}

	
}
