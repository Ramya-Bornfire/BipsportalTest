package com.bornfire.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Static_Merchant_Notification")
public class StaticMerchantNotificationEntity {
    @Id
    private String notification_id;
    private String merchant_id;
    private String user_id;
    private String device_id;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date tran_date;
    private char notification_flag;
    private BigDecimal tran_amount;
	public String getNotification_id() {
		return notification_id;
	}
	public void setNotification_id(String notification_id) {
		this.notification_id = notification_id;
	}
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public char getNotification_flag() {
		return notification_flag;
	}
	public void setNotification_flag(char notification_flag) {
		this.notification_flag = notification_flag;
	}
	public BigDecimal getTran_amount() {
		return tran_amount;
	}
	public void setTran_amount(BigDecimal tran_amount) {
		this.tran_amount = tran_amount;
	}
	public StaticMerchantNotificationEntity(String notification_id, String merchant_id, String user_id,
			String device_id, Date tran_date, char notification_flag, BigDecimal tran_amount) {
		super();
		this.notification_id = notification_id;
		this.merchant_id = merchant_id;
		this.user_id = user_id;
		this.device_id = device_id;
		this.tran_date = tran_date;
		this.notification_flag = notification_flag;
		this.tran_amount = tran_amount;
	}
	public StaticMerchantNotificationEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
 