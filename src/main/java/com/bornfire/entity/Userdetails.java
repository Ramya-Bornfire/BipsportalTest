package com.bornfire.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;



@Entity
@Table(name="USER_DETAILS_TABLE")
@DynamicUpdate
public class Userdetails implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8226752641296731001L;


	@EmbeddedId
	Userdetailsid userdetailsid;
	
	
	private BigDecimal	account_number;
	private BigDecimal	country_code;
	
	private BigDecimal	otp;
	private String	password;
	private String	bank_name;
	private String	bank_code;
	private String	branch_name;
	private String	branch_code;
	private String	ifsc_code;
	private BigDecimal	no_password_attempts;
	private String	user_locked_flg;
	private String	customer_name;
	private String	del_flg;
	private String	entity_cre_flg;
	private Date	entity_cre_date;
	private String	email_id;
	
	

	public BigDecimal getAccount_number() {
		return account_number;
	}
	public void setAccount_number(BigDecimal account_number) {
		this.account_number = account_number;
	}
	public BigDecimal getCountry_code() {
		return country_code;
	}
	public void setCountry_code(BigDecimal country_code) {
		this.country_code = country_code;
	}
	public BigDecimal getOtp() {
		return otp;
	}
	public void setOtp(BigDecimal otp) {
		this.otp = otp;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public BigDecimal getNo_password_attempts() {
		return no_password_attempts;
	}
	public void setNo_password_attempts(BigDecimal no_password_attempts) {
		this.no_password_attempts = no_password_attempts;
	}
	public String getUser_locked_flg() {
		return user_locked_flg;
	}
	public void setUser_locked_flg(String user_locked_flg) {
		this.user_locked_flg = user_locked_flg;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getEntity_cre_flg() {
		return entity_cre_flg;
	}
	public void setEntity_cre_flg(String entity_cre_flg) {
		this.entity_cre_flg = entity_cre_flg;
	}
	public Date getEntity_cre_date() {
		return entity_cre_date;
	}
	public void setEntity_cre_date(Date entity_cre_date) {
		this.entity_cre_date = entity_cre_date;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	
	public Userdetailsid getUserdetailsid() {
		return userdetailsid;
	}
	public void setUserdetailsid(Userdetailsid userdetailsid) {
		this.userdetailsid = userdetailsid;
	}
	

}
