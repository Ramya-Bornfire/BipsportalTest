package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BIPS_RATEMAINT")
public class RateMaintainanceEntity {
	 
	private String	billing_currency;
	private String	settlement_currency;
	private BigDecimal	rate;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	effective_date;
	private Date	audit_date;
	@Id
	private String srl;
	public String getsrl() {
		return srl;
	}
	public void setsrl(String billing_currency) {
		this.srl = billing_currency;
	}
	public String getBilling_currency() {
		return billing_currency;
	}
	public void setBilling_currency(String billing_currency) {
		this.billing_currency = billing_currency;
	}
	public String getSettlement_currency() {
		return settlement_currency;
	}
	public void setSettlement_currency(String settlement_currency) {
		this.settlement_currency = settlement_currency;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public Date getEffective_date() {
		return effective_date;
	}
	public void setEffective_date(Date effective_date) {
		this.effective_date = effective_date;
	}
	public Date getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(Date audit_date) {
		this.audit_date = audit_date;
	}
	public RateMaintainanceEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "{billing_currency=" + billing_currency + ", settlement_currency="
				+ settlement_currency + ", rate=" + rate + ", effective_date=" + effective_date + ", audit_date="
				+ audit_date + ",srl="+srl+"}";
	}
	

}