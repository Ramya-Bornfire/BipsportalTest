package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerPayResponse {
	public String merchant_id;
	public String device_id;
	public String tran_status;
	public String cbs_tran_status;
	public BigDecimal amount;
	public String referencelabel;
	public String tran_id;
	public Date tran_date;
	public String merchant_name;
	public String merchant_addr;
	public String merchant_city;
	public String merchant_terminal;
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getTran_status() {
		return tran_status;
	}
	public void setTran_status(String tran_status) {
		this.tran_status = tran_status;
	}
	public String getCbs_tran_status() {
		return cbs_tran_status;
	}
	public void setCbs_tran_status(String cbs_tran_status) {
		this.cbs_tran_status = cbs_tran_status;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getReferencelabel() {
		return referencelabel;
	}
	public void setReferencelabel(String referencelabel) {
		this.referencelabel = referencelabel;
	}
	public String getTran_id() {
		return tran_id;
	}
	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}
	public String getMerchant_addr() {
		return merchant_addr;
	}
	public void setMerchant_addr(String merchant_addr) {
		this.merchant_addr = merchant_addr;
	}
	public String getMerchant_city() {
		return merchant_city;
	}
	public void setMerchant_city(String merchant_city) {
		this.merchant_city = merchant_city;
	}
	public String getMerchant_terminal() {
		return merchant_terminal;
	}
	public void setMerchant_terminal(String merchant_terminal) {
		this.merchant_terminal = merchant_terminal;
	}
	public CustomerPayResponse(String merchant_id, String device_id, String tran_status, String cbs_tran_status,
			BigDecimal amount, String referencelabel, String tran_id, Date tran_date, String merchant_name,
			String merchant_addr, String merchant_city, String merchant_terminal) {
		super();
		this.merchant_id = merchant_id;
		this.device_id = device_id;
		this.tran_status = tran_status;
		this.cbs_tran_status = cbs_tran_status;
		this.amount = amount;
		this.referencelabel = referencelabel;
		this.tran_id = tran_id;
		this.tran_date = tran_date;
		this.merchant_name = merchant_name;
		this.merchant_addr = merchant_addr;
		this.merchant_city = merchant_city;
		this.merchant_terminal = merchant_terminal;
	}
	public CustomerPayResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CustomerPayResponse [merchant_id=" + merchant_id + ", device_id=" + device_id + ", tran_status="
				+ tran_status + ", cbs_tran_status=" + cbs_tran_status + ", amount=" + amount + ", referencelabel="
				+ referencelabel + ", tran_id=" + tran_id + ", tran_date=" + tran_date + ", merchant_name="
				+ merchant_name + ", merchant_addr=" + merchant_addr + ", merchant_city=" + merchant_city
				+ ", merchant_terminal=" + merchant_terminal + "]";
	}
	
	
	
	
	
}
