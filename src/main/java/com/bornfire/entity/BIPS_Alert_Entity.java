package com.bornfire.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BIPS_ALERT")
public class BIPS_Alert_Entity {

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	transaction_date;
	@Id
	private String	transaction;
	private String	status;
	private String	readflag;
	public Date getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReadflag() {
		return readflag;
	}
	public void setReadflag(String readflag) {
		this.readflag = readflag;
	}
	public BIPS_Alert_Entity(Date transaction_date, String transaction, String status, String readflag) {
		super();
		this.transaction_date = transaction_date;
		this.transaction = transaction;
		this.status = status;
		this.readflag = readflag;
	}
	public BIPS_Alert_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
