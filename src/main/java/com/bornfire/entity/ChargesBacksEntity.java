package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ChargesBacksEntity {

	private Date tran_date;
	private String sequence_unique_id;
	private String tran_audit_number;
	private String merchant_bill_number;
	private Date	bill_date;
	private BigDecimal	bill_amount;
	private String tran_currency;
	private String  reversal_remarks; 
	private Date reversal_date;
	private BigDecimal  reversal_amount;
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public String getSequence_unique_id() {
		return sequence_unique_id;
	}
	public void setSequence_unique_id(String sequence_unique_id) {
		this.sequence_unique_id = sequence_unique_id;
	}
	public String getTran_audit_number() {
		return tran_audit_number;
	}
	public void setTran_audit_number(String tran_audit_number) {
		this.tran_audit_number = tran_audit_number;
	}
	public String getMerchant_bill_number() {
		return merchant_bill_number;
	}
	public void setMerchant_bill_number(String merchant_bill_number) {
		this.merchant_bill_number = merchant_bill_number;
	}
	public Date getBill_date() {
		return bill_date;
	}
	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}
	public BigDecimal getBill_amount() {
		return bill_amount;
	}
	public void setBill_amount(BigDecimal bill_amount) {
		this.bill_amount = bill_amount;
	}
	public String getTran_currency() {
		return tran_currency;
	}
	public void setTran_currency(String tran_currency) {
		this.tran_currency = tran_currency;
	}
	public String getReversal_remarks() {
		return reversal_remarks;
	}
	public void setReversal_remarks(String reversal_remarks) {
		this.reversal_remarks = reversal_remarks;
	}
	public Date getReversal_date() {
		return reversal_date;
	}
	public void setReversal_date(Date reversal_date) {
		this.reversal_date = reversal_date;
	}
	public BigDecimal getReversal_amount() {
		return reversal_amount;
	}
	public void setReversal_amount(BigDecimal reversal_amount) {
		this.reversal_amount = reversal_amount;
	}
	public ChargesBacksEntity(Date tran_date, String sequence_unique_id, String tran_audit_number,
			String merchant_bill_number, Date bill_date, BigDecimal bill_amount, String tran_currency,
			String reversal_remarks, Date reversal_date, BigDecimal reversal_amount) {
		super();
		this.tran_date = tran_date;
		this.sequence_unique_id = sequence_unique_id;
		this.tran_audit_number = tran_audit_number;
		this.merchant_bill_number = merchant_bill_number;
		this.bill_date = bill_date;
		this.bill_amount = bill_amount;
		this.tran_currency = tran_currency;
		this.reversal_remarks = reversal_remarks;
		this.reversal_date = reversal_date;
		this.reversal_amount = reversal_amount;
	}
	public ChargesBacksEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
